package io.github.cyclohexatriene.comment_backend_for_valine.controller;

import io.github.cyclohexatriene.comment_backend_for_valine.controller.request.ImportByJsonRequest;
import io.github.cyclohexatriene.comment_backend_for_valine.controller.response.ImportByJsonResult;
import io.github.cyclohexatriene.comment_backend_for_valine.service.CommentConsoleService;
import io.github.cyclohexatriene.comment_backend_for_valine.utils.JsonUtils;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/console")
@Slf4j
public class ConsoleController {

    @Autowired
    private CommentConsoleService commentConsoleService;

    @Value("${console.password}")
    private String consolePassword;

    /**
     * 通过JSON导入评论数据
     */
    @PostMapping("/importComments")
    public ResponseEntity<ImportByJsonResult> importComments(@RequestBody ImportByJsonRequest request) {
        try {
            // 校验入参
            if (Objects.isNull(request)
                    || StringUtils.isBlank(request.getRawJson())) {
                log.error("ConsoleController.importComments error, invalid request");
                return ResponseEntity.badRequest().body(ImportByJsonResult.ofFail("入参异常"));
            }
            // 校验控制台密码
            if (!Objects.equals(request.getPassword(), consolePassword)) {
                log.error("ConsoleController.importComments error, invalid password");
                return ResponseEntity.badRequest().body(ImportByJsonResult.ofFail("密码错误"));
            }
            // 导入数据
            commentConsoleService.importByJson(request.getRawJson());
            return ResponseEntity.ok().body(ImportByJsonResult.ofSuccess("成功"));
        } catch (Exception e) {
            log.error("ConsoleController.importComments error, request: {}", JsonUtils.toJson(request), e);
            return ResponseEntity.internalServerError().body(ImportByJsonResult.ofFail("服务异常"));
        }
    }

}

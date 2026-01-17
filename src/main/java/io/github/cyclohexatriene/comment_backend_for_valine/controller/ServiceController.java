package io.github.cyclohexatriene.comment_backend_for_valine.controller;

import com.google.common.base.MoreObjects;
import io.github.cyclohexatriene.comment_backend_for_valine.constans.TypeConstants;
import io.github.cyclohexatriene.comment_backend_for_valine.controller.request.CreateCommentRequest;
import io.github.cyclohexatriene.comment_backend_for_valine.controller.request.QueryCommentsRequest;
import io.github.cyclohexatriene.comment_backend_for_valine.controller.request.QuerySubCommentsRequest;
import io.github.cyclohexatriene.comment_backend_for_valine.controller.response.CreateCommentResult;
import io.github.cyclohexatriene.comment_backend_for_valine.controller.response.QueryCommentsResult;
import io.github.cyclohexatriene.comment_backend_for_valine.controller.response.QuerySubCommentsResult;
import io.github.cyclohexatriene.comment_backend_for_valine.dto.CommentDTO;
import io.github.cyclohexatriene.comment_backend_for_valine.service.CommentService;
import io.github.cyclohexatriene.comment_backend_for_valine.utils.CqlUtils;
import io.github.cyclohexatriene.comment_backend_for_valine.utils.JsonUtils;
import io.github.cyclohexatriene.comment_backend_for_valine.utils.converter.CommentConverter;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


@RestController
@RequestMapping("/1.1")
@Slf4j
@CrossOrigin(origins = "*")
public class ServiceController {

    @Autowired
    private CommentService commentService;

    /**
     * 查询根评论或根评论数量
     */
    @GetMapping("/classes/Comment")
    public ResponseEntity<QueryCommentsResult> queryComments(@ModelAttribute QueryCommentsRequest request) {
        log.info("ServiceController.queryComments request: {}", JsonUtils.toJson(request));
        try {
            if (Objects.isNull(request)
                    || StringUtils.isBlank(request.getWhere())) {
                log.error("ServiceController.queryComments error, bad request");
                return ResponseEntity.badRequest().body(null);
            }
            String decoded = URLDecoder.decode(request.getWhere(), StandardCharsets.UTF_8);
            Map<String, Object> where = JsonUtils.fromCamelJson(decoded, TypeConstants.MAP_STRING_OBJECT);
            if (Objects.isNull(where)) {
                log.error("ServiceController.queryComments error, bad request");
                return ResponseEntity.badRequest().body(null);
            }
            String url = where.get("url").toString();
            if (StringUtils.isBlank(url)) {
                log.error("ServiceController.queryComments error, url is blank.");
                return ResponseEntity.badRequest().body(null);
            }
            // 查询数量
            if (Objects.equals(request.getCount(), 1L)) {
                Long count = commentService.countRootComment(url);
                QueryCommentsResult result = new QueryCommentsResult();
                result.setResults(new ArrayList<>());
                result.setCount(count);
                return ResponseEntity.ok().body(result);
            }
            // 查询评论列表
            Long skip = MoreObjects.firstNonNull(request.getSkip(), 0L);
            Long limit = MoreObjects.firstNonNull(request.getLimit(), 10L);
            List<CommentDTO> commentDTOList = commentService.queryRootCommentByPage(url, skip, limit);
            QueryCommentsResult result = new QueryCommentsResult();
            result.setResults(commentDTOList);
            result.setCount(null);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error("ServiceController.queryComments error, request: {}", JsonUtils.toJson(request), e);
            return ResponseEntity.internalServerError().body(null);
        }
    }

    /**
     * 根据根评论ID查询子评论
     */
    @GetMapping("/cloudQuery")
    public ResponseEntity<QuerySubCommentsResult> querySubComments(@ModelAttribute QuerySubCommentsRequest request) {
        try {
            if (Objects.isNull(request)
                    || StringUtils.isBlank(request.getCql())) {
                log.error("ServiceController.querySubComments error, bad request.");
                return ResponseEntity.badRequest().body(null);
            }
            List<String> ridList = CqlUtils.parseRidListFromCql(request.getCql());
            List<CommentDTO> commentDTOList = commentService.querySubCommentByObjectIdList(ridList);
            QuerySubCommentsResult result = new QuerySubCommentsResult();
            result.setResults(commentDTOList);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error("ServiceController.querySubComments error, request: {}", JsonUtils.toJson(request), e);
            return ResponseEntity.internalServerError().body(null);
        }
    }

    /**
     * 提交评论
     */
    @PostMapping("/classes/Comment")
    public ResponseEntity<CreateCommentResult> createComment(@RequestBody CreateCommentRequest request) {
        try {
            if (Objects.isNull(request)
                    || StringUtils.isBlank(request.getComment())) {
                log.error("ServiceController.createComment error, bad request.");
                return ResponseEntity.badRequest().body(null);
            }
            CommentDTO commentDTO = CommentConverter.convert(request);
            Pair<String, Date> pair = commentService.createComment(commentDTO);
            if (Objects.isNull(pair)) {
                log.error("ServiceController.createComment error");
                return ResponseEntity.internalServerError().body(null);
            }
            CreateCommentResult result = new CreateCommentResult();
            result.setObjectId(pair.getKey());
            result.setCreatedAt(pair.getValue());
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            log.error("ServiceController.createComment error, request: {}", JsonUtils.toJson(request), e);
            return ResponseEntity.internalServerError().body(null);
        }
    }

}

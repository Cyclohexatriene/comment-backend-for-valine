package io.github.cyclohexatriene.comment_backend_for_valine.service.impl;

import io.github.cyclohexatriene.comment_backend_for_valine.constans.TypeConstants;
import io.github.cyclohexatriene.comment_backend_for_valine.utils.converter.CommentConverter;
import io.github.cyclohexatriene.comment_backend_for_valine.dto.CommentDTO;
import io.github.cyclohexatriene.comment_backend_for_valine.repository.CommentRepository;
import io.github.cyclohexatriene.comment_backend_for_valine.service.CommentConsoleService;
import io.github.cyclohexatriene.comment_backend_for_valine.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Slf4j
public class CommentConsoleServiceImpl implements CommentConsoleService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importByJson(String rawJson) {
        List<CommentDTO> commentList = JsonUtils.fromCamelJson(rawJson, TypeConstants.COMMENT_LIST);
        for (CommentDTO commentDTO : commentList) {
            int row = commentRepository.insert(CommentConverter.convert(commentDTO));
            if (row != 1) {
                log.error("CommentConsoleServiceImpl.importByJson insert failed.");
                throw new RuntimeException("failed to insert record");
            }
        }
    }

}

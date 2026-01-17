package io.github.cyclohexatriene.comment_backend_for_valine.service.impl;

import io.github.cyclohexatriene.comment_backend_for_valine.dto.CommentDTO;
import io.github.cyclohexatriene.comment_backend_for_valine.repository.CommentRepository;
import io.github.cyclohexatriene.comment_backend_for_valine.repository.model.Comment;
import io.github.cyclohexatriene.comment_backend_for_valine.service.CommentService;
import io.github.cyclohexatriene.comment_backend_for_valine.utils.ObjectIdUtils;
import io.github.cyclohexatriene.comment_backend_for_valine.utils.converter.CommentConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<CommentDTO> queryRootCommentByPage(String url, Long offset, Long limit) {
        List<Comment> commentList = commentRepository.selectRootCommentByPage(url, offset, limit);
        return CommentConverter.convertList(commentList);
    }

    @Override
    public Long countRootComment(String url) {
        return commentRepository.countRootCommentByUrl(url);
    }

    @Override
    public List<CommentDTO> querySubCommentByObjectIdList(List<String> objectIdList) {
        if (CollectionUtils.isEmpty(objectIdList)) {
            return new ArrayList<>();
        }
        List<Comment> commentList = commentRepository.selectByRidList(objectIdList);
        return CommentConverter.convertList(commentList);
    }

    @Override
    public Pair<String, Date> createComment(CommentDTO commentDTO) {
        String objectId = ObjectIdUtils.generateNewObjectId();
        Comment comment = CommentConverter.convert(commentDTO);
        comment.setObjectId(objectId);
        int row = commentRepository.insert(comment);
        if (row != 1) {
            log.error("CommentServiceImpl.createComment error, insert failed.");
            return null;
        }
        // 再查一下数据库，获取时间
        Comment insertedRecord = commentRepository.selectByObjectId(objectId);
        if (Objects.isNull(insertedRecord)) {
            log.error("CommentServiceImpl.createComment error, insert failed.");
            return null;
        }
        return Pair.of(insertedRecord.getObjectId(), insertedRecord.getCreatedAt());
    }

}

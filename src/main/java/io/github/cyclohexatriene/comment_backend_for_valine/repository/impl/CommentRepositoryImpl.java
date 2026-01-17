package io.github.cyclohexatriene.comment_backend_for_valine.repository.impl;

import io.github.cyclohexatriene.comment_backend_for_valine.repository.CommentRepository;
import io.github.cyclohexatriene.comment_backend_for_valine.repository.mapper.CommentMapper;
import io.github.cyclohexatriene.comment_backend_for_valine.repository.model.Comment;
import io.github.cyclohexatriene.comment_backend_for_valine.repository.model.CommentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private CommentMapper mapper;

    @Override
    public int insert(Comment comment) {
        return mapper.insertSelective(comment);
    }

    @Override
    public long countRootCommentByUrl(String url) {
        CommentExample example = new CommentExample();
        example.createCriteria()
                .andUrlEqualTo(url)
                .andRidEqualTo("");
        return mapper.countByExample(example);
    }

    @Override
    public List<Comment> selectRootCommentByPage(String url, Long offset, Long limit) {
        CommentExample example = new CommentExample();
        example.createCriteria()
                .andUrlEqualTo(url)
                .andRidEqualTo("");
        example.setOrderByClause(String.format("created_at DESC LIMIT %d,%d", offset, limit));
        return mapper.selectByExample(example);
    }

    @Override
    public List<Comment> selectByRidList(List<String> ridList) {
        CommentExample example = new CommentExample();
        example.createCriteria().andRidIn(ridList);
        example.setOrderByClause("created_at DESC");
        return mapper.selectByExample(example);
    }

    @Override
    public Comment selectByObjectId(String objectId) {
        CommentExample example = new CommentExample();
        example.createCriteria().andObjectIdEqualTo(objectId);
        List<Comment> commentList = mapper.selectByExample(example);
        if (CollectionUtils.isEmpty(commentList)) {
            return null;
        }
        return commentList.get(0);
    }

}

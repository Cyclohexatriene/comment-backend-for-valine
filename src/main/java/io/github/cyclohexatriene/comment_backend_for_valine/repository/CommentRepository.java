package io.github.cyclohexatriene.comment_backend_for_valine.repository;

import io.github.cyclohexatriene.comment_backend_for_valine.repository.model.Comment;

import java.util.List;

public interface CommentRepository {

    int insert(Comment comment);

    long countRootCommentByUrl(String url);

    List<Comment> selectRootCommentByPage(String url, Long offset, Long limit);

    List<Comment> selectByRidList(List<String> ridList);

    Comment selectByObjectId(String objectId);

}

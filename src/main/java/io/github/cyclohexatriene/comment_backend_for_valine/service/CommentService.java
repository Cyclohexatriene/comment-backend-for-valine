package io.github.cyclohexatriene.comment_backend_for_valine.service;

import io.github.cyclohexatriene.comment_backend_for_valine.dto.CommentDTO;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Date;
import java.util.List;

/**
 * 业务相关接口
 */
public interface CommentService {

    /**
     * 查询根评论列表
     */
    List<CommentDTO> queryRootCommentByPage(String url, Long offset, Long limit);

    /**
     * 查询根评论数量
     */
    Long countRootComment(String url);

    /**
     * 根据根评论ID查询子评论
     */
    List<CommentDTO> querySubCommentByObjectIdList(List<String> objectIdList);

    /**
     * 新建评论
     */
    Pair<String, Date> createComment(CommentDTO commentDTO);

}

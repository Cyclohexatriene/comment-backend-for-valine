package io.github.cyclohexatriene.comment_backend_for_valine.service;

import io.github.cyclohexatriene.comment_backend_for_valine.dto.CommentDTO;

import java.util.List;

/**
 * 控制台专用接口，不提供正常业务功能
 */
public interface CommentConsoleService {

    /**
     * 通过json字符串向数据库中插入数据，迁移数据用
     */
    void importByJson(String rawJson);

    /**
     * 拉取全量数据，备份用
     */
    List<CommentDTO> pullAllComments();

}

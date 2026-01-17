package io.github.cyclohexatriene.comment_backend_for_valine.service;

/**
 * 控制台专用接口，不提供正常业务功能
 */
public interface CommentConsoleService {

    /**
     * 通过json字符串向数据库中插入数据，迁移数据用
     */
    void importByJson(String rawJson);

}

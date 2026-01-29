package io.github.cyclohexatriene.comment_backend_for_valine.controller.response;

import io.github.cyclohexatriene.comment_backend_for_valine.dto.CommentDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PullAllCommentsResult implements Serializable {

    private Boolean isSuccess;

    private String message;

    private List<CommentDTO> commentList;

    public static PullAllCommentsResult ofSuccess(List<CommentDTO> commentList) {
        PullAllCommentsResult result = new PullAllCommentsResult();
        result.setIsSuccess(true);
        result.setCommentList(commentList);
        return result;
    }

    public static PullAllCommentsResult ofFail(String message) {
        PullAllCommentsResult result = new PullAllCommentsResult();
        result.setIsSuccess(false);
        result.setMessage(message);
        return result;
    }

}

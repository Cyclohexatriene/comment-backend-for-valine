package io.github.cyclohexatriene.comment_backend_for_valine.controller.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CreateCommentResult implements Serializable {

    private String objectId;

    private Date createdAt;

}

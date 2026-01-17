package io.github.cyclohexatriene.comment_backend_for_valine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
public class CommentDTO implements Serializable {

    private Long id;

    private String objectId;

    private String nick;

    private String ip;

    private String mail;

    private String link;

    private String url;

    @JsonProperty("QQAvatar")
    private String qqAvatar;

    private String ua;

    @JsonProperty("ACL")
    private Map<String, Object> acl;

    private String pid;

    private String rid;

    private Map<String, Object> insertedAt;

    private Date createdAt;

    private Date updatedAt;

    private String comment;

}

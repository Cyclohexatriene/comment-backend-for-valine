package io.github.cyclohexatriene.comment_backend_for_valine.controller.response;

import io.github.cyclohexatriene.comment_backend_for_valine.dto.CommentDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QueryCommentsResult implements Serializable {

    private List<CommentDTO> results;

    private Long count;

}

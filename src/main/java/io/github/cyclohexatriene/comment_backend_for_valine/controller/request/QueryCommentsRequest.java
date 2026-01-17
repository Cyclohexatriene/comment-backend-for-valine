package io.github.cyclohexatriene.comment_backend_for_valine.controller.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryCommentsRequest implements Serializable {

    private String where;

    private String order;

    private Long skip;

    private Long limit;

    private Long count;

}

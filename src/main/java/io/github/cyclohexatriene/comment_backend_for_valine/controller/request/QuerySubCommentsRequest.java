package io.github.cyclohexatriene.comment_backend_for_valine.controller.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class QuerySubCommentsRequest implements Serializable {

    private String cql;

}

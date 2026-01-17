package io.github.cyclohexatriene.comment_backend_for_valine.controller.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImportByJsonResult implements Serializable {

    private Boolean isSuccess;

    private String message;

    public static ImportByJsonResult ofSuccess(String message) {
        ImportByJsonResult result = new ImportByJsonResult();
        result.setIsSuccess(true);
        result.setMessage(message);
        return result;
    }

    public static ImportByJsonResult ofFail(String message) {
        ImportByJsonResult result = new ImportByJsonResult();
        result.setIsSuccess(false);
        result.setMessage(message);
        return result;
    }

}

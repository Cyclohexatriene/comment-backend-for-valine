package io.github.cyclohexatriene.comment_backend_for_valine.utils;

import java.util.UUID;

public class ObjectIdUtils {

    public static String generateNewObjectId() {
        String uuid = UUID.randomUUID().toString();
        // 去掉连字符
        return uuid.replaceAll("-", "");
    }

}

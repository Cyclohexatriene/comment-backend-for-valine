package io.github.cyclohexatriene.comment_backend_for_valine.constans;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.cyclohexatriene.comment_backend_for_valine.dto.CommentDTO;

import java.util.List;
import java.util.Map;

public class TypeConstants {

    public static final TypeReference<List<CommentDTO>> COMMENT_LIST = new TypeReference<>() {};

    public static final TypeReference<Map<String, Object>> MAP_STRING_OBJECT = new TypeReference<>() {};

}

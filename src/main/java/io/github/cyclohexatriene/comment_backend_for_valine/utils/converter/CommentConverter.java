package io.github.cyclohexatriene.comment_backend_for_valine.utils.converter;

import io.github.cyclohexatriene.comment_backend_for_valine.constans.TypeConstants;
import io.github.cyclohexatriene.comment_backend_for_valine.controller.request.CreateCommentRequest;
import io.github.cyclohexatriene.comment_backend_for_valine.dto.CommentDTO;
import io.github.cyclohexatriene.comment_backend_for_valine.repository.model.Comment;
import io.github.cyclohexatriene.comment_backend_for_valine.utils.JsonUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CommentConverter {

    public static Comment convert(CommentDTO source) {
        if (Objects.isNull(source)) {
            return null;
        }
        Comment comment = new Comment();
        comment.setId(source.getId());
        comment.setObjectId(source.getObjectId());
        comment.setNick(source.getNick());
        comment.setIp(source.getIp());
        comment.setMail(source.getMail());
        comment.setLink(source.getLink());
        comment.setUrl(source.getUrl());
        comment.setQqAvatar(source.getQqAvatar());
        comment.setUa(source.getUa());
        comment.setAcl(JsonUtils.toCamelJson(source.getAcl()));
        comment.setPid(source.getPid());
        comment.setRid(source.getRid());
        comment.setInsertedAt(JsonUtils.toCamelJson(source.getInsertedAt()));
        comment.setCreatedAt(source.getCreatedAt());
        comment.setUpdatedAt(source.getUpdatedAt());
        comment.setComment(source.getComment());
        return comment;
    }

    public static CommentDTO convert(Comment source) {
        if (Objects.isNull(source)) {
            return null;
        }
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(source.getId());
        commentDTO.setObjectId(source.getObjectId());
        commentDTO.setNick(source.getNick());
        commentDTO.setIp(source.getIp());
        commentDTO.setMail(source.getMail());
        commentDTO.setLink(source.getLink());
        commentDTO.setUrl(source.getUrl());
        commentDTO.setQqAvatar(source.getQqAvatar());
        commentDTO.setUa(source.getUa());
        commentDTO.setAcl(JsonUtils.fromCamelJson(source.getAcl(), TypeConstants.MAP_STRING_OBJECT));
        commentDTO.setPid(source.getPid());
        commentDTO.setRid(source.getRid());
        commentDTO.setInsertedAt(JsonUtils.fromCamelJson(source.getInsertedAt(), TypeConstants.MAP_STRING_OBJECT));
        commentDTO.setCreatedAt(source.getCreatedAt());
        commentDTO.setUpdatedAt(source.getUpdatedAt());
        commentDTO.setComment(source.getComment());
        return commentDTO;
    }

    public static List<CommentDTO> convertList(List<Comment> sourceList) {
        if (Objects.isNull(sourceList)) {
            return null;
        }
        return sourceList.stream()
                .filter(Objects::nonNull)
                .map(CommentConverter::convert)
                .collect(Collectors.toList());
    }

    public static CommentDTO convert(CreateCommentRequest request) {
        if (Objects.isNull(request)) {
            return null;
        }
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setNick(request.getNick());
        commentDTO.setIp(request.getIp());
        commentDTO.setMail(request.getMail());
        commentDTO.setLink(request.getLink());
        commentDTO.setUrl(request.getUrl());
        commentDTO.setQqAvatar(request.getQqAvatar());
        commentDTO.setUa(request.getUa());
        commentDTO.setAcl(request.getAcl());
        commentDTO.setPid(request.getPid());
        commentDTO.setRid(request.getRid());
        commentDTO.setInsertedAt(request.getInsertedAt());
        commentDTO.setComment(request.getComment());
        return commentDTO;
    }

}

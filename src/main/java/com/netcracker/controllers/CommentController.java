package com.netcracker.controllers;


import com.netcracker.controllers.request.comment.CommentCreateRequest;
import com.netcracker.controllers.request.comment.CommentUpdateRequest;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.NotBelongToAccountException;
import com.netcracker.models.Announcement;
import com.netcracker.models.Comment;
import com.netcracker.models.PojoBuilder.AnnouncementBuilder;
import com.netcracker.models.PojoBuilder.CommentBuilder;
import com.netcracker.secutity.jwt.JwtAccount;
import com.netcracker.services.CommentService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.math.BigInteger;


@RestController
@RequestMapping(value = "/comment/")
@PreAuthorize("hasAnyRole('ROLE_OWNER')")
@Validated
@Log4j
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody @Valid CommentCreateRequest comment, @AuthenticationPrincipal JwtAccount account) throws  DaoAccessException {
            commentService.createComment(new CommentBuilder()
                    .withBody(comment.getBody())
                    .withAnnouncement(new AnnouncementBuilder()
                            .withAnnouncementId(comment.getAnnouncementId()).build())
                    .build(), account.getId());
             return ResponseEntity.ok(HttpStatus.OK);
    }



    @RequestMapping(value = "{commentId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteComment(@AuthenticationPrincipal JwtAccount account,
                                        @NotNull(message = "cant be null")
                                        @Positive(message = "must be integer value more than 0")
                                        @PathVariable(value = "commentId") BigInteger commentId) throws  DaoAccessException, NotBelongToAccountException {

            commentService.deleteComment(commentId, account.getId());
            return ResponseEntity.ok(HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity updateComment(@AuthenticationPrincipal JwtAccount account, @Valid @RequestBody CommentUpdateRequest comment) throws
                                                                                                      DaoAccessException, NotBelongToAccountException {
            commentService.updateComment(new CommentBuilder()
                    .witCommentId(comment.getCommentId())
                    .withBody(comment.getBody())
                    .build(), account.getId());
            return ResponseEntity.ok(HttpStatus.OK);

    }
}

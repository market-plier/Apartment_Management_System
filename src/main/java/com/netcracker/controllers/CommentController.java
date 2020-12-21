package com.netcracker.controllers;


import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Comment;
import com.netcracker.secutity.jwt.JwtAccount;
import com.netcracker.services.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;


@RestController
@RequestMapping(value = "/comment/")
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_OWNER')")
    public ResponseEntity create(@RequestBody @Valid Comment comment, @AuthenticationPrincipal JwtAccount account) throws NullPointerException, DaoAccessException {
            commentService.createComment(comment, account.getId());
             return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "{commentId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyRole('ROLE_OWNER')")
    public ResponseEntity deleteComment(@AuthenticationPrincipal JwtAccount account, @PathVariable String commentId) {


            commentService.deleteComment(new BigInteger(commentId), account.getId());
            return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('ROLE_OWNER')")
    public ResponseEntity updateComment(@AuthenticationPrincipal JwtAccount account, @RequestBody @Valid Comment comment) {

            commentService.updateComment(comment, account.getId());
             return ResponseEntity.ok(HttpStatus.OK);
    }
}

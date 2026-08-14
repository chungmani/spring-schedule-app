package com.example.springscheduleapp.comment.controller;

import com.example.springscheduleapp.comment.dto.CreateCommentRequest;
import com.example.springscheduleapp.comment.dto.CreateCommentResponse;
import com.example.springscheduleapp.comment.service.CommentService;
import com.example.springscheduleapp.common.Validate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules/{scheduleId}/comments")
public class CommentController {

    private final CommentService commentService;
    private final Validate validate;

    @PostMapping
    public ResponseEntity<CreateCommentResponse> create(
            @PathVariable Long scheduleId, @RequestBody CreateCommentRequest request ) {
        validate.validateCommentContent(request.getContent());
        return ResponseEntity.ok(commentService.create(scheduleId, request));
    }

}

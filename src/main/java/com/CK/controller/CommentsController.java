package com.CK.controller;

import com.CK.dto.request.CreateCommentRequestDto;
import com.CK.dto.request.FindCommentsRequestDto;
import com.CK.dto.request.UpdateCommentRequestDto;
import com.CK.dto.response.CommentResponseDto;
import com.CK.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.CK.constant.RestApiUrls.*;

@RestController
@RequestMapping(COMMENT)
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping(WRITE_COMMENT)
    public ResponseEntity<CommentResponseDto> writeComment(@RequestBody CreateCommentRequestDto dto) {
        return ResponseEntity.ok(commentsService.writeComment(dto));
    }

    @PostMapping(UPDATE_COMMENT)
    public ResponseEntity<CommentResponseDto> updateComment(@RequestBody UpdateCommentRequestDto dto) {
        return ResponseEntity.ok(commentsService.updateComment(dto));
    }

    @DeleteMapping(DELETE_COMMENT)
    public ResponseEntity<Boolean> deleteComment(@RequestParam String id) {
        return ResponseEntity.ok(commentsService.deleteComment(id));
    }

    @GetMapping(FIND_ALL_USER_COMMENTS)
    public ResponseEntity<List<CommentResponseDto>> findAllUserComments(@RequestParam String token) {
        return ResponseEntity.ok(commentsService.findAllUserComments(token));
    }

    @GetMapping(FIND_ALL_HOTEL_COMMENTS)
    public ResponseEntity<List<CommentResponseDto>> findAllHotelComments(@RequestBody FindCommentsRequestDto dto) {
        return ResponseEntity.ok(commentsService.findAllHotelComments(dto));
    }
}

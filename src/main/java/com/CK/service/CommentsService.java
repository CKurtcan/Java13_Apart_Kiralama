package com.CK.service;

import com.CK.dto.request.CreateCommentRequestDto;
import com.CK.dto.request.FindCommentsRequestDto;
import com.CK.dto.request.UpdateCommentRequestDto;
import com.CK.dto.response.CommentResponseDto;
import com.CK.entity.Comments;
import com.CK.entity.Hotel;
import com.CK.exception.ErrorType;
import com.CK.exception.HotelReservationManagerException;
import com.CK.mapper.ReservationMapper;
import com.CK.repository.CommentsRepository;
import com.CK.repository.HotelRepository;
import com.CK.repository.UserProfileRepository;
import com.CK.utility.other.JWTManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final UserProfileRepository userProfileRepository;
    private final HotelRepository hotelRepository;
    private final JWTManager jwtManager;
    private final ReservationMapper reservationMapper;
    private final HotelService hotelService;

    public CommentResponseDto writeComment(CreateCommentRequestDto dto) {
        // token kontrol eklenecek
        Comments comment = reservationMapper.INSTANCE.fromCreateCommentRequestDtoToComments(dto);
        commentsRepository.save(comment);
        String id = String.valueOf(jwtManager.getIdFromToken(dto.getToken()));
        String username = userProfileRepository.findUsernameById(id);
        String hotelName = hotelRepository.findNameById(dto.getHotelId());
        hotelService.updateRating(dto.getRating(), dto.getHotelId());
        return reservationMapper.INSTANCE.fromCommentsToCommentResponseDto(username, hotelName, dto.getRating(), dto.getDate(), dto.getComment());
    }

    public CommentResponseDto updateComment(UpdateCommentRequestDto dto) {
        // token kontrol eklenecek
        Optional<Comments> optionalComments = commentsRepository.findById(dto.getId());
        if (optionalComments.isEmpty()) {
            throw new HotelReservationManagerException(ErrorType.COMMENT_NOT_FOUND);
        }
        Comments comments = optionalComments.get();
        comments.setComment(dto.getComment());
        String id = String.valueOf(jwtManager.getIdFromToken(dto.getToken()));
        String username = userProfileRepository.findUsernameById(id);
        String hotelName = hotelRepository.findNameById(comments.getHotelId());
        hotelService.updateRating(comments.getRating(), comments.getHotelId());
        return reservationMapper.INSTANCE.fromCommentsToCommentResponseDto(username, hotelName, comments.getRating(), dto.getDate(), dto.getComment());
    }

    public Boolean deleteComment(String id) {
        // token kontrol eklenecek
        commentsRepository.delete(commentsRepository.findById(id).get());
        return true;
    }

    public List<CommentResponseDto> findAllUserComments(String token) {
        // token kontrol eklenecek
        List<CommentResponseDto> dtoList = new ArrayList<>();
        String id = String.valueOf(jwtManager.getIdFromToken(token));
        List<Comments> comments = commentsRepository.findAllByUserId(id);
        comments.stream().forEach(comment -> {
            String username = userProfileRepository.findUsernameById(comment.getUserId());
            String hotelName = hotelRepository.findNameById(comment.getHotelId());
            dtoList.add(reservationMapper.INSTANCE.fromCommentsToCommentResponseDto(username, hotelName, comment.getRating(), comment.getDate(), comment.getComment()));
        });
        return dtoList;
    }

    public List<CommentResponseDto> findAllHotelComments(FindCommentsRequestDto dto) {
        // token kontrol eklenecek
        List<CommentResponseDto> dtoList = new ArrayList<>();
        Hotel hotel= hotelRepository.findIdByName(dto.getHotelName());
        List<Comments> comments = commentsRepository.findAllByHotelId(hotel.getId());
        comments.stream().forEach(comment -> {
            String username = userProfileRepository.findUsernameById(comment.getUserId());
            dtoList.add(reservationMapper.INSTANCE.fromCommentsToCommentResponseDto(username, dto.getHotelName(), comment.getRating(), comment.getDate(), comment.getComment()));
        });
        return dtoList;
    }
}
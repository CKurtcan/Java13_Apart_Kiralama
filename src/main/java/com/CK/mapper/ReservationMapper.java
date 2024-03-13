package com.CK.mapper;

import com.CK.dto.request.AddHotelRequestDto;
import com.CK.dto.request.CreateCommentRequestDto;
import com.CK.dto.request.CreateProfileRequestDto;
import com.CK.dto.request.RegisterRequestDto;
import com.CK.dto.response.CheckRoleResponseDto;
import com.CK.dto.response.CommentResponseDto;
import com.CK.dto.response.HotelResponseDto;
import com.CK.entity.Auth;
import com.CK.entity.Comments;
import com.CK.entity.Hotel;
import com.CK.entity.UserProfile;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    Auth fromRegisterRequestDtoToAuth(final RegisterRequestDto dto);

    UserProfile fromCreateProfileRequestDtoToUserProfile(final CreateProfileRequestDto dto);

    CreateProfileRequestDto fromAuthToCreateProfileDto(final Auth auth);

    Hotel fromAddHotelRequestDtoToHotel(final AddHotelRequestDto dto);

    CheckRoleResponseDto fromIdAndRoleToCheckRoleResponseDto(final String id, final String role);

    Comments fromCreateCommentRequestDtoToComments(final CreateCommentRequestDto dto);

    CommentResponseDto fromCommentsToCommentResponseDto(final String username, final String hotelName, final Double rating, final String date, final String comment);

    HotelResponseDto fromHotelToHotelResponseDto(final Hotel hotel);
}

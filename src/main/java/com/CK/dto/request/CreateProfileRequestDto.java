package com.CK.dto.request;

import com.CK.utility.enums.ERoles;
import com.CK.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProfileRequestDto {

    private String username;
    private String password;
    private String email;
    private String phone;
    private ERoles role;
    private EStatus status;
}

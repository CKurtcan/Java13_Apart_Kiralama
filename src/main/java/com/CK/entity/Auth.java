package com.CK.entity;

import com.CK.utility.enums.ERoles;
import com.CK.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Auth extends BaseEntity{

    @Id
    private String id;

    private String username;
    private String password;
    private String email;
    private String phone;
    private ERoles role;

    @Builder.Default
    private EStatus status = EStatus.PENDING;

}

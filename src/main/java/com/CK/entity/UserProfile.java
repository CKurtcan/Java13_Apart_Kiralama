package com.CK.entity;

import com.CK.utility.enums.ERoles;
import com.CK.utility.enums.EStatus;
import lombok.AllArgsConstructor;
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
public class UserProfile extends BaseEntity {

    @Id
    private String id;

    private String idNum;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private ERoles role;
    private EStatus status;
    private String authId;

}

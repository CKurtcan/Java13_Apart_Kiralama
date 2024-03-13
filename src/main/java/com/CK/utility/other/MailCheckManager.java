package com.CK.utility.other;


import com.CK.utility.enums.ERoles;
import org.springframework.stereotype.Service;

@Service
public class MailCheckManager {

    public ERoles checkMail(String mail) {
        int index = mail.indexOf("@");
        if (mail.substring(index).equals("@vvtadmin.com")) {
            return ERoles.ROLE_ADMIN;
        } else if (mail.substring(index).equals("@vvt.com")) {
            return ERoles.ROLE_MODERATOR;
        }else {
            return ERoles.ROLE_USER;
        }
    }
}

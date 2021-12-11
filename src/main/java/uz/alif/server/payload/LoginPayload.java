package uz.alif.server.payload;// Author - Orifjon Yunusjonov
// t.me/coderr24

import lombok.Data;

@Data
public class LoginPayload {
    private String username;

    private String password;

}

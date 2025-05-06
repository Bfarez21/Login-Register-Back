package com.practicabf.jwt.loginregisterformback.model.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

   // private  String email;
    private String dni;
    private String name;
    private String apellido;
    private String password;
    private String username;
}

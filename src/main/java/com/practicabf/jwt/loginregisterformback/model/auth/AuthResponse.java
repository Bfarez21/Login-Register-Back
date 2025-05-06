package com.practicabf.jwt.loginregisterformback.model.auth;

import com.practicabf.jwt.loginregisterformback.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    String token;
    Usuario usuario;
}

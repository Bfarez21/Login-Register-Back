package com.practicabf.jwt.loginregisterformback.controllers.auth;

import com.practicabf.jwt.loginregisterformback.model.Usuario;
import com.practicabf.jwt.loginregisterformback.model.auth.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import com.practicabf.jwt.loginregisterformback.jwt.JwtService;
import com.practicabf.jwt.loginregisterformback.model.auth.AuthResponse;
import com.practicabf.jwt.loginregisterformback.model.auth.LoginRequest;
import com.practicabf.jwt.loginregisterformback.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails userDetails= usuarioRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow();
        String token = jwtService.getToken(userDetails);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    // service register
    public AuthResponse register(RegisterRequest request){
        Usuario usuario= Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .apellido(request.getApellido())
                .dni(request.getDni())
                .build();
        usuarioRepository.save(usuario);
        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }

    // Realiza la consulta en la base de datos para verificar si existe un usuario con el mismo correo electrónico
    public boolean isEmailAlreadyRegistered(String email){
        Optional<Usuario> userOptional = usuarioRepository.findByUsername(email);
        return userOptional.isPresent();
    }
}

package com.practicabf.jwt.loginregisterformback.controllers.auth;

import com.practicabf.jwt.loginregisterformback.model.Usuario;
import com.practicabf.jwt.loginregisterformback.model.auth.AuthResponse;
import com.practicabf.jwt.loginregisterformback.model.auth.LoginRequest;
import com.practicabf.jwt.loginregisterformback.model.auth.RegisterRequest;
import com.practicabf.jwt.loginregisterformback.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest loginRequest){
        AuthResponse authResponse = authService.login(loginRequest);
        Usuario usuario = usuarioRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(()-> new RuntimeException("Usuario not found"));
        authResponse.setUsuario(usuario);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/register")
    private ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        if (authService.isEmailAlreadyRegistered(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Correo"+ request.getUsername() + "ya registrado",null));
        }
        return ResponseEntity.ok(authService.register(request));
    }
}

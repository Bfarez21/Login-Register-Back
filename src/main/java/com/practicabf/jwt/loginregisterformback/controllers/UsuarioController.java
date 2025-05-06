package com.practicabf.jwt.loginregisterformback.controllers;

import com.fasterxml.jackson.databind.node.LongNode;
import com.practicabf.jwt.loginregisterformback.model.Usuario;
import com.practicabf.jwt.loginregisterformback.repository.UsuarioRepository;
import exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;

    // listar todos los usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios(){return usuarioRepository.findAll();}

    //buscar por id
    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Usuario not found with id"+ id));
    }

    // crear usuarios
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){return usuarioRepository.save(usuario);}

    //actualiar usuarios by id
    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuarioDetails){
        Usuario usuario= usuarioRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Empleado not found with id: " + id));

        usuario.setName(usuarioDetails.getName());
        usuario.setUsername(usuarioDetails.getUsername());
        if (!usuario.getPassword().equals(usuarioDetails.getPassword()))
            usuario.setPassword(passwordEncoder.encode(usuarioDetails.getPassword()));
        usuario.setDni(usuarioDetails.getDni());
        usuario.setApellido(usuario.getApellido());
        return usuarioRepository.save(usuario);
    }

    // eliminar usuarios by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id){
        Usuario usuario= usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado not found with id: " + id));
        usuarioRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

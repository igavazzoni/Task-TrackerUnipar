package com.example.tasktracker; 

 

import org.springframework.http.HttpStatus; 

import org.springframework.http.ResponseEntity; 

import org.springframework.web.bind.annotation.*; 

 

import java.util.Map; 

 

@RestController 

@RequestMapping("/api/habitos") 

public class UsuarioController { 

 

    // Usuário fixo 

    private final Usuario usuario = new Usuario(); 

 

    public UsuarioController() { 

        usuario.setId(1L); 

        usuario.setNome("usuarioPadrao"); 

        usuario.setEmail("usuario@example.com"); 

        usuario.setSenha("senha123"); 

    } 

 

    @PostMapping("/login") 

    public ResponseEntity<String> login(@RequestBody Map<String, String> loginData) { 

        String nome = loginData.get("nome"); 

        String senha = loginData.get("senha"); 

 

        if (nome.equals(usuario.getNome()) && senha.equals(usuario.getSenha())) { 

            return ResponseEntity.ok("Login bem-sucedido"); 

        } else { 

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas"); 

        } 

    } 

 

    @GetMapping("/{id}") 

    public Usuario getUsuario(@PathVariable Long id) { 

        return usuario; 

    } 

} 

 

 
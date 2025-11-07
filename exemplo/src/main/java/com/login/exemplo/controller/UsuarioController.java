package com.login.exemplo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.login.exemplo.dto.UsuarioRequestDTO;
import com.login.exemplo.dto.UsuarioResponseDTO;
import com.login.exemplo.entity.Usuario;
import com.login.exemplo.repositories.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping(value = "cadastro")
    public ResponseEntity<String> saveUser(@Valid @RequestBody UsuarioRequestDTO user) {
        Usuario usuario = new Usuario(user.getName(), user.getEmail(), user.getPassword());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("O usuário foi cadastrado com sucesso!");
    }

    @PostMapping(value ="login")
    public ResponseEntity<String> login(@RequestBody Usuario user) {
        Usuario findUser = usuarioRepository.findByEmail(user.getEmail());
        if (findUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        } else if (findUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok("Usuário logado!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
        }
    }

    @GetMapping(value = "listar/Fiama")
    public List<UsuarioResponseDTO> listarUsuario1() {
    	List<Usuario> usuarios = usuarioRepository.findAll();
    	List<UsuarioResponseDTO> listaDeUsuarios = new ArrayList<>();
    	for (Usuario usuario : usuarios) {
    		listaDeUsuarios.add(new UsuarioResponseDTO(usuario));	
    	}
        return listaDeUsuarios;
    }

    @GetMapping("usuario/{id}")
    public ResponseEntity<?> usuarioPorId(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.isPresent()
                ? ResponseEntity.ok(usuario.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    }

    @PutMapping("usuario/{id}")
    public ResponseEntity<?> atualizar(@PathVariable int id, @RequestBody Usuario novoUsuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario usuario = usuarioExistente.get();
            usuario.setName(novoUsuario.getName());
            usuario.setPassword(novoUsuario.getPassword());
            usuarioRepository.save(usuario);
            return ResponseEntity.ok("Usuário atualizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }

    @DeleteMapping("usuario/{id}")
    public ResponseEntity<?> deletar(@PathVariable int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok("Usuário excluído com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe");
        }
    }
}




package com.example.jurassicpark.controllers;

import com.example.jurassicpark.models.UserDTO;
import com.example.jurassicpark.models.entidades.User;
import com.example.jurassicpark.models.entidades.Roles;
import com.example.jurassicpark.repository.RolesRepository;
import com.example.jurassicpark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO) {
        // Verifica si el usuario ya existe
        Optional<User> existingUser = userRepository.findByUsername(userDTO.getUsername());
        if (existingUser.isPresent()) {
            return "El usuario ya existe";
        }

        // Crea y guarda un nuevo usuario
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setEnabled(true);

        // Asigna roles
        Roles role = rolesRepository.findByRoleName(userDTO.getRole())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + userDTO.getRole()));
        newUser.getRoles().add(role);

        userRepository.save(newUser);

        return "Usuario registrado con éxito";
    }
}

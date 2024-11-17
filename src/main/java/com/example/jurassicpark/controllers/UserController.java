package com.example.jurassicpark.controllers;
import com.example.jurassicpark.models.UserDTO;
import com.example.jurassicpark.models.entidades.User;
import com.example.jurassicpark.models.entidades.Roles;
import com.example.jurassicpark.repository.RolesRepository;
import com.example.jurassicpark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 1. Registrar un nuevo usuario
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        // Verifica si el usuario ya existe
        Optional<User> existingUser = userRepository.findByUsername(userDTO.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("El usuario ya existe");
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

        return ResponseEntity.ok().body("Usuario registrado con éxito");
    }

    // 2. Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    // 3. Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.get());
    }

    // 4. Actualizar un usuario
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User userToUpdate = existingUser.get();
        userToUpdate.setUsername(userDTO.getUsername());
        userToUpdate.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(userToUpdate);

        return ResponseEntity.ok("Usuario actualizado con éxito");
    }

    // 5. Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("Usuario eliminado con éxito");
    }
}



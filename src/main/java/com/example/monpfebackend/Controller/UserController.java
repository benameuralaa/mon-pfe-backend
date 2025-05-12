package com.example.monpfebackend.Controller;

import com.example.monpfebackend.Entity.Utilisateur;
import com.example.monpfebackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    // ✅ Récupérer les utilisateurs (optionnellement par rôle)
    @GetMapping
    public List<Utilisateur> getUsers(@RequestParam(required = false) String role) {
        if (role != null) {
            try {
                Utilisateur.Role userRole = Utilisateur.Role.valueOf(role.toUpperCase());
                return userService.getUsersByRole(userRole);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Rôle invalide : " + role);
            }
        } else {
            return userService.getAllUsers();
        }
    }

    // Ajouter un nouvel utilisateur
    @PostMapping("/add")
    public ResponseEntity<Utilisateur> addUser(@RequestBody Utilisateur utilisateur) {
        Utilisateur saved = userService.addUser(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Mettre à jour un utilisateur existant
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUser(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        utilisateur.setId(id);
        Utilisateur updated = userService.updateUser(utilisateur);
        return ResponseEntity.ok(updated);
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

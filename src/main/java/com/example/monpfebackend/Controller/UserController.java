package com.example.monpfebackend.Controller;

import com.example.monpfebackend.Entity.Utilisateur;
import com.example.monpfebackend.Repository.AuthRepository;
import com.example.monpfebackend.Repository.UserRepository;
import com.example.monpfebackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users") // URL de base pour les utilisateurs
@CrossOrigin(origins = "http://localhost:4200") // Angular port
public class UserController {

    @Autowired
    private UserService userService;

    // Récupérer la liste de tous les utilisateurs
    @GetMapping
    public List<Utilisateur> getAllUsers() {
        return userService.getAllUsers();
    }

    // Ajouter un nouvel utilisateur
    @PostMapping("/add")
    public ResponseEntity<Utilisateur> addUser(@RequestBody Utilisateur utilisateur) {
        Utilisateur saved = userService.addUser(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        //Code HTTP : 201 CREATED
        // body(saved) = on renvoie le nouvel objet créé dans le corps de la réponse
    }

    // Mettre à jour un utilisateur existant
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUser(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        utilisateur.setId(id);
        Utilisateur updated = userService.updateUser(utilisateur);
        return ResponseEntity.ok(updated); // updated : on renvoie l’objet mis à jour
    }

    // Supprimer un utilisateur par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // Code HTTP : 204 NO CONTENT , Réponse vide après suppression
    }
}

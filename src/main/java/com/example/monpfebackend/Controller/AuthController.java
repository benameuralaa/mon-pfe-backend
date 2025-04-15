package com.example.monpfebackend.Controller;

import com.example.monpfebackend.Entity.Utilisateur;
import com.example.monpfebackend.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") // Préfixe commun
@CrossOrigin(origins = "http://localhost:4200") // Autoriser Angular à communiquer avec le backend
public class AuthController {

    @Autowired
    private AuthService authService;

    // Endpoint pour l'inscription
    @PostMapping("/register")
    public String register(@RequestBody Utilisateur utilisateur) {
        boolean success = authService.registerUtilisateur(utilisateur);
        return success ? "Inscription réussie" : "Email déjà utilisé";
    }

    // Endpoint pour la connexion
    @PostMapping("/login")
    public Object login(@RequestBody Utilisateur utilisateur) {
        Utilisateur authenticatedUser = authService.authenticateUtilisateur(utilisateur);

        if (authenticatedUser != null) {
            return authenticatedUser; // Renvoyer l'utilisateur connecté
        } else {
            return "Email ou mot de passe incorrect";
        }
    }
}

package com.example.monpfebackend.Service;

import com.example.monpfebackend.Entity.Utilisateur;
import com.example.monpfebackend.Repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    // Méthode d'inscription
    public boolean registerUtilisateur(Utilisateur utilisateur) {
        // Vérifier si l'email existe déjà
        if (authRepository.findByEmail(utilisateur.getEmail()) != null) {
            return false; // Échec : utilisateur existe déjà
        }

        // Sauvegarder l'utilisateur
        authRepository.save(utilisateur);
        return true; // Succès
    }

    // Méthode de connexion
    public Utilisateur authenticateUtilisateur(Utilisateur utilisateur) {
        Utilisateur existingUtilisateur = authRepository.findByEmail(utilisateur.getEmail());

        // Vérifier le mot de passe
        if (existingUtilisateur != null && existingUtilisateur.getMotDePasse().equals(utilisateur.getMotDePasse())) {
            return existingUtilisateur;
        }

        return null;
    }
}

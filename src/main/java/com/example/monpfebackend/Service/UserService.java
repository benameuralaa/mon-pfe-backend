package com.example.monpfebackend.Service;

import com.example.monpfebackend.Entity.Utilisateur;
import com.example.monpfebackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Récupérer tous les utilisateurs
    public List<Utilisateur> getAllUsers() {
        return userRepository.findAll();
    }

    // Ajouter un nouvel utilisateur
    public Utilisateur addUser(Utilisateur utilisateur) {
        return userRepository.save(utilisateur);
    }

    // Modifier un utilisateur existant
    public Utilisateur updateUser(Utilisateur utilisateur) {
        return userRepository.save(utilisateur);
    }

    // Supprimer un utilisateur par ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}

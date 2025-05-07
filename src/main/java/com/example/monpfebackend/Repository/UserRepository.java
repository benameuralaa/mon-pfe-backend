package com.example.monpfebackend.Repository;

import com.example.monpfebackend.Entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Utilisateur, Long> {

    // Trouver un utilisateur par son email
    Utilisateur findByEmail(String email);

    // Trouver un utilisateur par son ID
    Optional<Utilisateur> findById(Long id);
}


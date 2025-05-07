package com.example.monpfebackend.Repository;

import com.example.monpfebackend.Entity.Groupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupeRepository extends JpaRepository<Groupe, Long> {
    // Retrouver un groupe par son nom
    Optional<Groupe> findByNom(String nom);
}


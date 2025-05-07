package com.example.monpfebackend.Service;

import com.example.monpfebackend.Entity.Groupe;
import com.example.monpfebackend.Entity.Utilisateur;
import com.example.monpfebackend.Repository.GroupeRepository;
import com.example.monpfebackend.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class GroupeService {

    @Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private UserRepository userRepository;

    // Récupérer tous les groupes
    public List<Groupe> getAllGroupes() {
        return groupeRepository.findAll();
    }

    // Récupérer un groupe par son ID
    public Groupe getGroupeById(Long id) {
        return groupeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Groupe introuvable avec id : " + id));
    }

    // Créer un nouveau groupe
    public Groupe createGroupe(Groupe groupe) {
        return groupeRepository.save(groupe);
    }

    // Mettre à jour un groupe existant
    public Groupe updateGroupe(Long id, Groupe updatedGroupe) {
        Groupe groupe = getGroupeById(id);
        groupe.setNom(updatedGroupe.getNom());
        return groupeRepository.save(groupe);
    }

    // Supprimer un groupe par ID
    public void deleteGroupe(Long id) {
        if (!groupeRepository.existsById(id)) {
            throw new RuntimeException("Groupe introuvable avec id : " + id);
        }
        groupeRepository.deleteById(id);
    }
}

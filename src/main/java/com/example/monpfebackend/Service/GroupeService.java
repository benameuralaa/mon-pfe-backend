package com.example.monpfebackend.Service;

import com.example.monpfebackend.Entity.Groupe;
import com.example.monpfebackend.Entity.Utilisateur;
import com.example.monpfebackend.Repository.GroupeRepository;
import com.example.monpfebackend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupeService {

    @Autowired
    private GroupeRepository groupeRepository;

    @Autowired
    private UserRepository userRepository;

    // Créer un groupe
    public Groupe createGroupe(Groupe groupe, List<Long> intervenantIds) {
        // Trouver les intervenants à partir des IDs
        List<Utilisateur> intervenants = userRepository.findAllById(intervenantIds)
                .stream()
                .filter(u -> u.getRole() == Utilisateur.Role.INTERVENANT)
                .collect(Collectors.toList());

        // Lier les intervenants au groupe
        groupe.setIntervenants(intervenants);

        // Sauvegarder le groupe
        Groupe savedGroupe = groupeRepository.save(groupe);

        // Mettre à jour le groupe pour chaque intervenant
        intervenants.forEach(intervenant -> {
            intervenant.setGroupe(savedGroupe);  // Associer le groupe à l'utilisateur
            userRepository.save(intervenant);  // Sauvegarder l'utilisateur avec le groupe mis à jour
        });

        return savedGroupe;
    }

    // Modifier un groupe
    public Groupe updateGroupe(Groupe groupe, List<Long> newIntervenantIds) {
        Groupe existingGroupe = groupeRepository.findById(groupe.getId()).orElseThrow();

        // Mettre à jour le nom
        existingGroupe.setNom(groupe.getNom());

        // Gérer les intervenants
        List<Utilisateur> newIntervenants = userRepository.findAllById(newIntervenantIds)
                .stream()
                .filter(u -> u.getRole() == Utilisateur.Role.INTERVENANT)
                .collect(Collectors.toList());

        // Retirer les anciens liens
        existingGroupe.getIntervenants().forEach(ancien -> {
            if (!newIntervenants.contains(ancien)) {
                ancien.setGroupe(null);
                userRepository.save(ancien);
            }
        });

        // Ajouter les nouveaux liens
        newIntervenants.forEach(nouveau -> {
            if (!existingGroupe.getIntervenants().contains(nouveau)) {
                nouveau.setGroupe(existingGroupe);
                userRepository.save(nouveau);
            }
        });

        return groupeRepository.save(existingGroupe);
    }

    // Afficher la liste de tous les groupes
    public List<Groupe> getAllGroupes() {
        return groupeRepository.findAll();
    }

    //Supprimer un groupe
    public void deleteGroupe(Long id) {
        Groupe groupe = groupeRepository.findById(id).orElseThrow();

        // Nettoyer les relations
        groupe.getIntervenants().forEach(intervenant -> {
            intervenant.setGroupe(null);
            userRepository.save(intervenant);
        });

        groupeRepository.delete(groupe);
    }

    // Récupérer un groupe par son ID
    public Groupe getGroupeById(Long id) {
        return groupeRepository.findById(id).orElseThrow();
    }
}
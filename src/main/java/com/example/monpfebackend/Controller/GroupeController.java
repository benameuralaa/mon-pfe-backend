package com.example.monpfebackend.Controller;

import com.example.monpfebackend.Entity.Groupe;
import com.example.monpfebackend.Service.GroupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groupes")
@CrossOrigin(origins = "*")
public class GroupeController {

    @Autowired
    private GroupeService groupeService;

    // Ajouter un utilisateur à un groupe
    @PostMapping("/assign/utilisateur/{utilisateurId}/groupe/{groupeId}")
    public ResponseEntity<String> assignUtilisateurToGroupe(@PathVariable Long utilisateurId, @PathVariable Long groupeId) {
        String response = groupeService.ajouterUtilisateurAuGroupe(utilisateurId, groupeId);
        return ResponseEntity.ok(response);
    }
    // Liste de tous les groupes
    @GetMapping
    public List<Groupe> getAllGroupes() {
        return groupeService.getAllGroupes();
    }

    // Récupérer un groupe par son ID : Détails d’un groupe.
    @GetMapping("/{id}")
    public Groupe getGroupeById(@PathVariable Long id) {
        return groupeService.getGroupeById(id);
    }

    // Créer un nouveau groupe
    @PostMapping
    public Groupe createGroupe(@RequestBody Groupe groupe) {
        return groupeService.createGroupe(groupe);
    }

    // Modifier un groupe existant
    @PutMapping("/{id}")
    public Groupe updateGroupe(@PathVariable Long id, @RequestBody Groupe groupe) {
        return groupeService.updateGroupe(id, groupe);
    }

    // Endpoint pour supprimer un groupe par ID
    @DeleteMapping("/{id}")
    public void deleteGroupe(@PathVariable Long id) {
        groupeService.deleteGroupe(id);
    }
}

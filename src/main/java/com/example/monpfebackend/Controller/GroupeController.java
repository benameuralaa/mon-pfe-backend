package com.example.monpfebackend.Controller;

import com.example.monpfebackend.Entity.Groupe;
import com.example.monpfebackend.Service.GroupeService;
import com.example.monpfebackend.dto.GroupeDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/groupes")
@CrossOrigin(origins = "http://localhost:4200")
public class GroupeController {

    @Autowired
    private GroupeService groupeService;

    @PostMapping
    public ResponseEntity<Groupe> createGroupe(@RequestBody Groupe groupe, @RequestParam List<Long> intervenantIds) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(groupeService.createGroupe(groupe, intervenantIds));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Groupe> updateGroupe(@PathVariable Long id,
                                               @RequestBody Groupe groupe,
                                               @RequestParam List<Long> intervenantIds) {
        groupe.setId(id);
        return ResponseEntity.ok(groupeService.updateGroupe(groupe, intervenantIds));
    }

    @GetMapping
    public ResponseEntity<List<GroupeDto>> getAllGroupes() {
        List<Groupe> groupes = groupeService.getAllGroupes();
        List<GroupeDto> dtos = groupes.stream().map(GroupeDto::new).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupe(@PathVariable Long id) {
        groupeService.deleteGroupe(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Groupe> getGroupeById(@PathVariable Long id) {
        return ResponseEntity.ok(groupeService.getGroupeById(id));
    }
}
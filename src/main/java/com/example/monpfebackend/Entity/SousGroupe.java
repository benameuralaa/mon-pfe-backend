package com.example.monpfebackend.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class SousGroupe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String description;

    // Chaque sous-groupe appartient à un groupe
    @ManyToOne
    @JoinColumn(name = "groupe_id")
    private Groupe groupe;

    // Un sous-groupe peut avoir plusieurs tickets
    @OneToMany(mappedBy = "sousGroupe", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}

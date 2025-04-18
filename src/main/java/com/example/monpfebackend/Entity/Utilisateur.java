package com.example.monpfebackend.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Utilisateur {

    public enum Role {
        ADMIN,
        INTERVENANT,
        UTILISATEUR
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Un utilisateur peut créer plusieurs tickets
    @OneToMany(mappedBy = "utilisateur")
    private List<Ticket> ticketsCréés;

    // Un utilisateur peut être assigné comme intervenant à plusieurs tickets
    @OneToMany(mappedBy = "intervenant")
    private List<Ticket> ticketsAssignés;

    // Un utilisateur peut écrire plusieurs commentaires
    @OneToMany(mappedBy = "utilisateur")
    private List<com.example.monpfebackend.Entity.Commentaire> commentaires;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Ticket> getTicketsCréés() {
        return ticketsCréés;
    }

    public void setTicketsCréés(List<Ticket> ticketsCréés) {
        this.ticketsCréés = ticketsCréés;
    }

    public List<Ticket> getTicketsAssignés() {
        return ticketsAssignés;
    }

    public void setTicketsAssignés(List<Ticket> ticketsAssignés) {
        this.ticketsAssignés = ticketsAssignés;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }
}


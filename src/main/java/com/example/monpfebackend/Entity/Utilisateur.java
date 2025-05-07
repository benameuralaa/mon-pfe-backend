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

    @Column(nullable = false)
    private String nom;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Un intervenant appartient à un groupe
    @ManyToOne(optional = false)  //
    @JoinColumn(name = "groupe_id")
    private Groupe groupe;


    // Un UTILISATEUR normal peut créer plusieurs tickets
    @OneToMany(mappedBy = "createur" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> ticketsCréés;    // Tickets créés par cet utilisateur

    // Un INTERVENANT peut être assigné à plusieurs tickets
    @OneToMany(mappedBy = "intervenant")
    private List<Ticket> ticketsAssignés; // Tickets où il est intervenant

    // Un utilisateur peut écrire plusieurs commentaires
    @OneToMany(mappedBy = "auteur" , cascade = CascadeType.ALL)
    private List<com.example.monpfebackend.Entity.Commentaire> commentaires;

    // Un utilisateur peut ajouter plusieurs pièces jointes
    @OneToMany(mappedBy = "uploader" , cascade = CascadeType.ALL)
    private List<PieceJointe> piecesJointes;

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
    public List<PieceJointe> getPiecesJointes() {
        return piecesJointes;
    }

    public void setPiecesJointes(List<PieceJointe> piecesJointes) {
        this.piecesJointes = piecesJointes;
    }

}



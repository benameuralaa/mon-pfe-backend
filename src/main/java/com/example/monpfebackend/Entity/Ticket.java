package com.example.monpfebackend.Entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Ticket {

    public enum Statut {
        EN_ATTENTE,
        EN_COURS,
        TRAITE
    }

    public enum Urgence {
        FAIBLE,
        MOYENNE,
        HAUTE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    @Enumerated(EnumType.STRING)
    private Urgence urgence;

    // Créateur du ticket
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    // Intervenant assigné
    @ManyToOne
    @JoinColumn(name = "intervenant_id")
    private Utilisateur intervenant;

    @ManyToOne
    @JoinColumn(name = "groupe_id")
    private Groupe groupe;

    @ManyToOne
    @JoinColumn(name = "sous_groupe_id")
    private SousGroupe sousGroupe;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Urgence getUrgence() {
        return urgence;
    }

    public void setUrgence(Urgence urgence) {
        this.urgence = urgence;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Utilisateur getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Utilisateur intervenant) {
        this.intervenant = intervenant;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public SousGroupe getSousGroupe() {
        return sousGroupe;
    }

    public void setSousGroupe(SousGroupe sousGroupe) {
        this.sousGroupe = sousGroupe;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }
}

package myapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sorties")
public class Sortie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(length = 2000)
    private String description;

    @Column
    private String siteWeb;

    @Column(nullable = false)
    private LocalDate dateSortie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "createur_id", nullable = false)
    private Member createur;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Sortie() {
    }

    public Sortie(String nom, String description, String siteWeb, LocalDate dateSortie,
                  Member createur, Category category) {
        this.nom = nom;
        this.description = description;
        this.siteWeb = siteWeb;
        this.dateSortie = dateSortie;
        this.createur = createur;
        this.category = category;
    }

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

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public Member getCreateur() {
        return createur;
    }

    public void setCreateur(Member createur) {
        this.createur = createur;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Sortie{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", siteWeb='" + siteWeb + '\'' +
                ", dateSortie=" + dateSortie +
                '}';
    }
}
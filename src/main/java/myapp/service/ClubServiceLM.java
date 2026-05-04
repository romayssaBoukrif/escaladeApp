package myapp.service;

import myapp.dao.ClubDao;
import myapp.dao.jpa.SortieSearchFilter;
import myapp.model.Category;
import myapp.model.Member;
import myapp.model.Sortie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClubServiceLM implements ClubService {

    private final ClubDao dao;

    //pour comparer les mots de passe
    private final PasswordEncoder passwordEncoder;

    public ClubServiceLM(ClubDao dao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> listerCategories() {
        return dao.findAllCategories();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> getCategorieAvecSorties(Long id) {
        // Récupère une catégorie avec ses sorties déjà chargées
        return dao.findCategorieWithSorties(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Sortie> getSortie(Long id) {
        return dao.findSortieById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sortie> rechercherSorties(SortieSearchFilter criteria) {
        // Lance une recherche multicritère sur les sorties
        return dao.searchSorties(criteria);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> authentifier(String email, String motDePasseClair) {
        return dao.findMembreByEmail(email)
                .filter(m -> passwordEncoder.matches(motDePasseClair, m.getMotDePasse()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sortie> listerSortiesDuMembre(Long membreId) {
        return dao.findSortiesByCreateur(membreId);
    }

    @Override
    public Sortie creerSortie(Long membreId, Long categorieId, Sortie sortie) {
        Member membre = dao.findMembreById(membreId).orElseThrow();
        Category categorie = dao.findCategorieById(categorieId).orElseThrow();

        // force l'id à null pour garantir la création d'une nouvelle sortie
        sortie.setId(null);
        sortie.setCreateur(membre);
        sortie.setCategory(categorie);

        // Sauvegarde de la nouvelle sortie
        return dao.saveSortie(sortie);
    }

    @Override
    public Sortie modifierSortie(Long membreId, Long sortieId, Long categorieId, Sortie nouvelleValeur) {
        Sortie existante = dao.findSortieById(sortieId).orElseThrow();

        // Vérifie que le membre connecté est bien le créateur de la sortie
        if (!existante.getCreateur().getId().equals(membreId)) {
            throw new SecurityException("Vous ne pouvez modifier que vos propres sorties.");
        }

        Category categorie = dao.findCategorieById(categorieId).orElseThrow();

        // Mise à jour des champs modifiables
        existante.setNom(nouvelleValeur.getNom());
        existante.setDescription(nouvelleValeur.getDescription());
        existante.setSiteWeb(nouvelleValeur.getSiteWeb());
        existante.setDateSortie(nouvelleValeur.getDateSortie());
        existante.setCategory(categorie);

        // Sauvegarde de la sortie modifiée
        return dao.saveSortie(existante);
    }

    @Override
    public void supprimerSortie(Long membreId, Long sortieId) {
        Sortie existante = dao.findSortieById(sortieId).orElseThrow();

        // Vérifie que seul le créateur peut supprimer sa sortie
        if (!existante.getCreateur().getId().equals(membreId)) {
            throw new SecurityException("Vous ne pouvez supprimer que vos propres sorties.");
        }

        // Suppression de la sortie
        dao.deleteSortie(sortieId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> findMembreByEmail(String email) {
        return dao.findMembreByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> listerMembres() {
        return dao.findAllMembres();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> getMembreAvecSorties(Long id) {
        return dao.findMembreWithSorties(id);
    }
}
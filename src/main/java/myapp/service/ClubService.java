package myapp.service;


import myapp.dao.jpa.SortieSearchFilter;
import myapp.model.Category;
import myapp.model.Member;
import myapp.model.Sortie;

import java.util.List;
import java.util.Optional;

public interface ClubService {

    List<Category> listerCategories();

    Optional<Category> getCategorieAvecSorties(Long id);

    Optional<Sortie> getSortie(Long id);

    List<Sortie> rechercherSorties(SortieSearchFilter filter);

    Optional<Member> authentifier(String email, String motDePasseClair);

    List<Sortie> listerSortiesDuMembre(Long membreId);

    Sortie creerSortie(Long membreId, Long categorieId, Sortie sortie);

    Sortie modifierSortie(Long membreId, Long sortieId, Long categorieId, Sortie nouvelleValeur);

    void supprimerSortie(Long membreId, Long sortieId);

    Optional<Member> findMembreByEmail(String email);

    List<Member> listerMembres();

    Optional<Member> getMembreAvecSorties(Long id);
}
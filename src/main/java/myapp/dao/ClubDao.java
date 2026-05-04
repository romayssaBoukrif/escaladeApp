package myapp.dao;


import myapp.dao.jpa.SortieSearchFilter;
import myapp.model.Category;
import myapp.model.Member;
import myapp.model.Sortie;

import java.util.List;
import java.util.Optional;

public interface ClubDao {

    List<Category> findAllCategories();

    Optional<Category> findCategorieById(Long id);

    Optional<Category> findCategorieWithSorties(Long id);

    Category saveCategory(Category category);

    List<Member> findAllMembres();

    Optional<Member> findMembreById(Long id);

    Optional<Member> findMembreByEmail(String email);

    Optional<Member> findMembreWithSorties(Long id);

    Member saveMember(Member membre);

    Optional<Sortie> findSortieById(Long id);

    List<Sortie> searchSorties(SortieSearchFilter filtre);

    List<Sortie> findSortiesByCreateur(Long membreId);

    Sortie saveSortie(Sortie sortie);

    void deleteSortie(Long id);
}
package myapp.dao.jpa;

import myapp.dao.ClubDao;
import myapp.model.Category;
import myapp.model.Member;
import myapp.model.Sortie;
import myapp.repo.CategoryRepository;
import myapp.repo.MemberRepository;
import myapp.repo.SortieRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class JpaClubDao implements ClubDao {

    // Repositories Spring Data JPA utilisés pour accéder aux entités en base
    private final CategoryRepository categorieRepository;
    private final MemberRepository membreRepository;
    private final SortieRepository sortieRepository;

    public JpaClubDao(CategoryRepository categorieRepository,
                      MemberRepository membreRepository,
                      SortieRepository sortieRepository) {
        this.categorieRepository = categorieRepository;
        this.membreRepository = membreRepository;
        this.sortieRepository = sortieRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAllCategories() {
        return categorieRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findCategorieById(Long id) {
        return categorieRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findCategorieWithSorties(Long id) {
        // Récupère une catégorie avec ses sorties déjà chargées
        return categorieRepository.findDetailedById(id);
    }



    @Override
    public Category saveCategory(Category categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> findAllMembres() {
        return membreRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> findMembreById(Long id) {
        return membreRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> findMembreByEmail(String email) {
        return membreRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Member> findMembreWithSorties(Long id) {
        // Récupère un membre avec ses sorties déjà chargées
        return membreRepository.findDetailedById(id);
    }


    @Override
    public Member saveMember(Member membre) {
        return membreRepository.save(membre);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Sortie> findSortieById(Long id) {
        return sortieRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sortie> searchSorties(SortieSearchFilter criteria) {
        // Recherche multicritère construite dynamiquement
        return sortieRepository.findAll(SortieFilters.byCriteria(criteria));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sortie> findSortiesByCreateur(Long membreId) {
        return sortieRepository.findByCreateurId(membreId);
    }

    @Override
    public Sortie saveSortie(Sortie sortie) {
        return sortieRepository.save(sortie);
    }

    @Override
    public void deleteSortie(Long id) {
        sortieRepository.deleteById(id);
    }
}
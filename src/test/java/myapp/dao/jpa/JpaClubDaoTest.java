package myapp.dao.jpa;

import myapp.dao.ClubDao;
import myapp.model.Category;
import myapp.model.Member;
import myapp.model.Sortie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;
import jakarta.persistence.EntityManager;



@SpringBootTest
@Transactional
class JpaClubDaoTest {

    @Autowired
    private ClubDao dao;

    @PersistenceContext
    private EntityManager entityManager;



    private Category category;
    private Member member;
    private Sortie sortie;
    private String unique;


    @BeforeEach
    void setUp() {
        unique = UUID.randomUUID().toString().substring(0, 8);

        category = dao.saveCategory(new Category("CategorieTest-" + unique));

        member = dao.saveMember(new Member(
                "NomTest-" + unique,
                "PrenomTest-" + unique,
                "test-" + unique + "@club.fr",
                "motdepasse"
        ));

        sortie = dao.saveSortie(new Sortie(
                "SortieTest-" + unique,
                "Description test " + unique,
                "https://exemple.fr/" + unique,
                LocalDate.now().plusDays(10),
                member,
                category
        ));

        entityManager.flush();
        entityManager.clear();
    }


    @Test
    void categoriesDoiventEtreListees() {
        assertFalse(dao.findAllCategories().isEmpty());
    }

    @Test
    void doitTrouverCategorieParId() {
        var resultat = dao.findCategorieById(category.getId());

        assertTrue(resultat.isPresent());
        assertEquals(category.getNom(), resultat.get().getNom());
    }

    @Test
    void doitTrouverCategorieAvecSorties() {
        var resultat = dao.findCategorieWithSorties(category.getId());

        assertTrue(resultat.isPresent());
        assertNotNull(resultat.get().getSorties());
        assertFalse(resultat.get().getSorties().isEmpty());
    }

    @Test
    void doitListerLesMembres() {
        assertFalse(dao.findAllMembres().isEmpty());
    }

    @Test
    void doitTrouverMembreParId() {
        var resultat = dao.findMembreById(member.getId());

        assertTrue(resultat.isPresent());
        assertEquals(member.getEmail(), resultat.get().getEmail());
    }

    @Test
    void doitTrouverMembreParEmail() {
        var resultat = dao.findMembreByEmail(member.getEmail());

        assertTrue(resultat.isPresent());
        assertEquals(member.getPrenom(), resultat.get().getPrenom());
    }

    @Test
    void doitTrouverMembreAvecSorties() {
        var resultat = dao.findMembreWithSorties(member.getId());

        assertTrue(resultat.isPresent());
        assertNotNull(resultat.get().getSorties());
        assertFalse(resultat.get().getSorties().isEmpty());
    }

    @Test
    void doitTrouverSortieParId() {
        var resultat = dao.findSortieById(sortie.getId());

        assertTrue(resultat.isPresent());
        assertEquals(sortie.getNom(), resultat.get().getNom());
    }

    @Test
    void doitTrouverSortiesParCreateur() {
        var resultats = dao.findSortiesByCreateur(member.getId());

        assertNotNull(resultats);
        assertFalse(resultats.isEmpty());
        assertTrue(resultats.stream().allMatch(s -> s.getCreateur().getId().equals(member.getId())));
    }

    @Test
    void doitPouvoirCreerUneCategorie() {
        Category nouvelle = dao.saveCategory(new Category("NouvelleCategorie-" + unique + "-x"));

        assertNotNull(nouvelle.getId());
        assertEquals("NouvelleCategorie-" + unique + "-x", nouvelle.getNom());
    }

    @Test
    void doitPouvoirCreerUnMembre() {
        Member nouveau = dao.saveMember(new Member(
                "Durand",
                "Julie",
                "julie-" + unique + "@club.fr",
                "secret"
        ));

        assertNotNull(nouveau.getId());
        assertEquals("julie-" + unique + "@club.fr", nouveau.getEmail());
    }

    @Test
    void doitPouvoirCreerUneSortie() {
        Sortie nouvelle = new Sortie(
                "NouvelleSortie-" + unique,
                "Description nouvelle sortie",
                "https://exemple.fr/nouvelle/" + unique,
                LocalDate.now().plusDays(20),
                member,
                category
        );

        Sortie saved = dao.saveSortie(nouvelle);

        assertNotNull(saved.getId());
        assertEquals("NouvelleSortie-" + unique, saved.getNom());
    }

    @Test
    void rechercheParNomDoitFonctionner() {
        SortieSearchFilter criteria = new SortieSearchFilter();
        criteria.setNom("SortieTest-" + unique);

        var resultats = dao.searchSorties(criteria);

        assertFalse(resultats.isEmpty());
        assertTrue(resultats.stream().anyMatch(s -> s.getNom().contains("SortieTest-" + unique)));
    }

    @Test
    void rechercheParCategorieDoitFonctionner() {
        SortieSearchFilter criteria = new SortieSearchFilter();
        criteria.setCategoryId(category.getId());

        var resultats = dao.searchSorties(criteria);

        assertFalse(resultats.isEmpty());

        assertTrue(resultats.stream().allMatch(s -> s.getCategory().getId().equals(category.getId())));
    }

    @Test
    void doitPouvoirSupprimerUneSortie() {
        Long id = sortie.getId();

        dao.deleteSortie(id);

        assertTrue(dao.findSortieById(id).isEmpty());
    }
}
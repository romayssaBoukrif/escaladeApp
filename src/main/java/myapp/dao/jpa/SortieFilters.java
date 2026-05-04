package myapp.dao.jpa;

import myapp.model.Sortie;
import org.springframework.data.jpa.domain.Specification;

/**
 * Classe utilitaire permettant de construire dynamiquement
 * une requête de recherche sur les sorties à partir de plusieurs critères.
 */
public final class SortieFilters {

    //Constructeur privé pour empêcher l'instanciation de cette classe.
    private SortieFilters() {
    }

    /**
     * Construit une spécification JPA à partir des critères fournis.
     * Cette spécification sera utilisée par Spring Data JPA
     * pour filtrer les sorties selon les champs renseignés.
     *
     * @param criteria objet contenant les différents critères de recherche
     * @return une Specification<Sortie> correspondant aux filtres demandés
     */
    public static Specification<Sortie> byCriteria(SortieSearchFilter criteria) {
        return (root, query, cb) -> {

            // On ajoutera  les filtres un par un avec des AND.
            var predicates = cb.conjunction();

            // Si un nom est fourni, on filtre les sorties dont le nom contient ce texte
            if (criteria.getNom() != null && !criteria.getNom().isBlank()) {
                predicates = cb.and(predicates,
                        cb.like(
                                cb.lower(root.get("nom")),
                                "%" + criteria.getNom().toLowerCase() + "%"
                        ));
            }

            // Si un identifiant de catégorie est fourni,
            // on ne garde que les sorties appartenant à cette catégorie.
            if (criteria.getCategoryId() != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("category").get("id"), criteria.getCategoryId()));
            }

            // Si un identifiant de créateur est fourni,
            // on ne garde que les sorties créées par ce membre.
            if (criteria.getCreateurId() != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("createur").get("id"), criteria.getCreateurId()));
            }

            // Si une date minimale est fournie,
            // on garde uniquement les sorties dont la date est >= à cette date.
            if (criteria.getDateMin() != null) {
                predicates = cb.and(predicates,
                        cb.greaterThanOrEqualTo(root.get("dateSortie"), criteria.getDateMin()));
            }

            // Si une date maximale est fournie,
            // on garde uniquement les sorties dont la date est <= à cette date.
            if (criteria.getDateMax() != null) {
                predicates = cb.and(predicates,
                        cb.lessThanOrEqualTo(root.get("dateSortie"), criteria.getDateMax()));
            }

            // On retourne l'ensemble des conditions construites.
            return predicates;
        };
    }
}
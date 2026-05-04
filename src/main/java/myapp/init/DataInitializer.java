
package myapp.init;

import myapp.dao.ClubDao;
import myapp.model.Category;
import myapp.model.Member;
import myapp.model.Sortie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(ClubDao dao, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!dao.findAllCategories().isEmpty()) {
                return;
            }

            Random random = new Random();

            List<Category> categories = new ArrayList<>();
            List<Member> members = new ArrayList<>();

            List<String> categoryNames = List.of(
                    "Escalade sportive",
                    "Grande voie",
                    "Bloc",
                    "Terrain d’aventure",
                    "Via ferrata",
                    "Randonnée alpine",
                    "Escalade en falaise",
                    "Escalade en salle",
                    "Alpinisme rocheux",
                    "Découverte débutants",
                    "Perfectionnement falaise",
                    "Sortie club week-end",
                    "Escalade enfants",
                    "Sortie famille",
                    "Manipulations de corde",
                    "Progression en tête",
                    "Travail de lecture",
                    "Sortie technique",
                    "Voies longues",
                    "Escalade côtière",
                    "Escalade montagne",
                    "Falaise école",
                    "Sortie indoor",
                    "Préparation grande voie",
                    "Initiation bloc",
                    "Bloc confirmé",
                    "Escalade plaisir",
                    "Sortie découverte nature",
                    "Autonomie en falaise",
                    "Entraînement extérieur"
            );

            for (String categoryName : categoryNames) {
                categories.add(dao.saveCategory(new Category(categoryName)));
            }



            String[] noms = {
                    "Bernard", "Petit", "Robert", "Richard", "Dubois", "Moreau", "Simon",
                    "Laurent", "Michel", "Lefebvre", "Garcia", "David", "Roux", "Fournier",
                    "Girard", "Andre", "Mercier", "Blanc", "Guerin", "Boyer",
                    "Chevalier", "Francois", "Legrand", "Gauthier", "Perrin", "Robin",
                    "Clement", "Morin", "Henry", "Rousseau","wilson","boukrif","smyth"
            };

            String[] prenoms = {
                    "Lucas", "Emma", "Louis", "Jade", "Hugo", "Lina", "Nathan", "Chloe",
                    "Leo", "Manon", "Enzo", "Sarah", "Tom", "Camille", "Noah", "Ines",
                    "Arthur", "Lea", "Adam", "Zoe", "Paul", "Clara", "Jules", "Eva",
                    "Gabriel", "Louise", "Mathis", "Nina", "Sacha", "Julia","Garrett",
                    "Romayssa","Bobbie"

            };

            for (int i = 1; i <= 300; i++) {
                String nom = noms[random.nextInt(noms.length)];
                String prenom = prenoms[random.nextInt(prenoms.length)];
                String email = "membre" + i + "@club.fr";

                Member member = dao.saveMember(new Member(
                        nom,
                        prenom,
                        email,
                        passwordEncoder.encode("test123")
                ));
                members.add(member);
            }


            String[] lieux = {
                    "Calanques", "Verdon", "Buoux", "Fontainebleau", "Céüse",
                    "Dentelles de Montmirail", "Orpierre", "Presles", "Annot",
                    "Chamonix", "Ailefroide", "Sainte-Victoire", "Briançon",
                    "La Palud-sur-Verdon", "Omblèze", "Mont Dauphin", "Gorges du Tarn",
                    "Les Aravis", "Vercors", "Saint-Léger-du-Ventoux",
                    "Sormiou", "Morgiou", "En-Vau", "Pic Saint-Loup", "Albarracín",
                    "La Sainte-Baume", "Corbières", "Les Écrins", "Annecy", "Lyon"
            };

            String[] themes = {
                    "initiation", "perfectionnement", "grande voie", "bloc", "falaise",
                    "découverte", "journée technique", "sortie club", "progression",
                    "travail de lecture", "sortie débutants", "sortie confirmés",
                    "autonomie", "escalade plaisir", "préparation", "travail gestuelle",
                    "résistance", "sortie montagne", "atelier sécurité", "sortie nature"
            };


            for (int i = 1; i <= 5000; i++) {
                String lieu = lieux[random.nextInt(lieux.length)];
                String theme = themes[random.nextInt(themes.length)];

                Category category = categories.get(random.nextInt(categories.size()));
                Member member = members.get(random.nextInt(members.size()));

                Sortie sortie = new Sortie(
                        lieu + " - " + theme + " " + i,
                        "Sortie organisée sur le site de " + lieu + " autour du thème : " + theme + ".",
                        "https://exemple.fr/sortie/" + i,
                        LocalDate.now().plusDays(random.nextInt(365) + 1),
                        member,
                        category
                );

                dao.saveSortie(sortie);
            }

            System.out.println("Jeu de données créé : "
                    + categories.size() + " catégories, "
                    + members.size() + " membres, "
                    + "5000 sorties.");
        };
    }

    private Category findCategory(List<Category> categories, String name) {
        return categories.stream()
                .filter(c -> c.getNom().equals(name))
                .findFirst()
                .orElseThrow();
    }
}
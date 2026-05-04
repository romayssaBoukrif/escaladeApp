package myapp.web.controller;


import myapp.dao.jpa.SortieSearchFilter;
import myapp.service.ClubService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PublicController {

    private final ClubService clubService;

    public PublicController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/categories";
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", clubService.listerCategories());
        return "categories";
    }

    @GetMapping("/categories/{id}")
    public String category(@PathVariable Long id, Model model) {
        var category = clubService.getCategorieAvecSorties(id).orElseThrow();
        model.addAttribute("category", category);
        return "category";
    }

    @GetMapping("/sorties/{id}")
    public String sortie(@PathVariable Long id, Authentication authentication, Model model) {
        var sortie = clubService.getSortie(id).orElseThrow();
        model.addAttribute("sortie", sortie);
        model.addAttribute("authenticated", authentication != null && authentication.isAuthenticated()
                && !"anonymousUser".equals(authentication.getName()));
        return "sortie";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) Long categoryId,
            Model model) {

            SortieSearchFilter criteria = new SortieSearchFilter();
        criteria.setNom(nom);
        criteria.setCategoryId(categoryId);

        model.addAttribute("resultats", clubService.rechercherSorties(criteria));
        model.addAttribute("categories", clubService.listerCategories());
        return "search";
    }
}

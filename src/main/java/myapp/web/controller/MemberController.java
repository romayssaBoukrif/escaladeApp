package myapp.web.controller;

import jakarta.validation.Valid;
import myapp.model.Category;
import myapp.model.Sortie;
import myapp.service.ClubService;
import myapp.security.SortieValidator;
import myapp.web.form.SortieForm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/membre")
public class MemberController {

    private final ClubService clubService;
    private final SortieValidator sortieValidator;

    public MemberController(ClubService clubService, SortieValidator sortieValidator) {
        this.clubService = clubService;
        this.sortieValidator = sortieValidator;
    }

    private Long getCurrentMemberId(Authentication authentication) {
        return clubService.findMembreByEmail(authentication.getName())
                .orElseThrow()
                .getId();
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return clubService.listerCategories();
    }

    @GetMapping("/mes-sorties")
    public String mesSorties(Authentication authentication, Model model) {
        Long membreId = getCurrentMemberId(authentication);
        model.addAttribute("sorties", clubService.listerSortiesDuMembre(membreId));
        return "mes-sorties";
    }

    @GetMapping("/sorties/new")
    public String newForm(Model model) {
        model.addAttribute("sortieForm", new SortieForm());
        model.addAttribute("formAction", "/membre/sorties");
        model.addAttribute("pageTitle", "Créer une sortie");
        model.addAttribute("submitLabel", "Créer");
        return "sortie-form";
    }

    @PostMapping("/sorties")
    public String create(@Valid @ModelAttribute("sortieForm") SortieForm form,
                         BindingResult bindingResult,
                         Authentication authentication,
                         Model model) {

        sortieValidator.validate(form, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/membre/sorties");
            model.addAttribute("pageTitle", "Créer une sortie");
            model.addAttribute("submitLabel", "Créer");
            return "sortie-form";
        }

        Long membreId = getCurrentMemberId(authentication);

        Sortie sortie = new Sortie();
        sortie.setNom(form.getNom());
        sortie.setDescription(form.getDescription());
        sortie.setSiteWeb(form.getSiteWeb());
        sortie.setDateSortie(form.getDateSortie());

        clubService.creerSortie(membreId, form.getCategoryId(), sortie);
        return "redirect:/membre/mes-sorties";
    }

    @GetMapping("/sorties/{id}/edit")
    public String editForm(@PathVariable Long id,
                           Authentication authentication,
                           Model model) {
        Long membreId = getCurrentMemberId(authentication);
        Sortie sortie = clubService.getSortie(id).orElseThrow();

        if (!sortie.getCreateur().getId().equals(membreId)) {
            throw new SecurityException("Vous ne pouvez modifier que vos propres sorties.");
        }

        SortieForm form = new SortieForm();
        form.setNom(sortie.getNom());
        form.setDescription(sortie.getDescription());
        form.setSiteWeb(sortie.getSiteWeb());
        form.setDateSortie(sortie.getDateSortie());
        form.setCategoryId(sortie.getCategory().getId());

        model.addAttribute("sortieForm", form);
        model.addAttribute("formAction", "/membre/sorties/" + id + "/edit");
        model.addAttribute("pageTitle", "Modifier une sortie");
        model.addAttribute("submitLabel", "Enregistrer");

        return "sortie-form";
    }

    @PostMapping("/sorties/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("sortieForm") SortieForm form,
                         BindingResult bindingResult,
                         Authentication authentication,
                         Model model) {

        sortieValidator.validate(form, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("formAction", "/membre/sorties/" + id + "/edit");
            model.addAttribute("pageTitle", "Modifier une sortie");
            model.addAttribute("submitLabel", "Enregistrer");
            return "sortie-form";
        }

        Long membreId = getCurrentMemberId(authentication);

        Sortie sortie = new Sortie();
        sortie.setNom(form.getNom());
        sortie.setDescription(form.getDescription());
        sortie.setSiteWeb(form.getSiteWeb());
        sortie.setDateSortie(form.getDateSortie());

        clubService.modifierSortie(membreId, id, form.getCategoryId(), sortie);
        return "redirect:/membre/mes-sorties";
    }

    @PostMapping("/sorties/{id}/delete")
    public String delete(@PathVariable Long id, Authentication authentication) {
        Long membreId = getCurrentMemberId(authentication);
        clubService.supprimerSortie(membreId, id);
        return "redirect:/membre/mes-sorties";
    }
}
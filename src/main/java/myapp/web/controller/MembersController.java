package myapp.web.controller;

import myapp.service.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MembersController {

    private final ClubService clubService;

    public MembersController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/membres")
    public String membres(Model model) {
        model.addAttribute("membres", clubService.listerMembres());
        return "membres";
    }

    @GetMapping("/membres/{id}")
    public String membre(@PathVariable Long id, Model model) {
        var membre = clubService.getMembreAvecSorties(id).orElseThrow();
        model.addAttribute("membre", membre);
        return "membre";
    }
}
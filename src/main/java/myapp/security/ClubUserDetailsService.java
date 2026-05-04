package myapp.security;

import myapp.service.ClubService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClubUserDetailsService implements UserDetailsService {

    private final ClubService clubService;

    public ClubUserDetailsService(ClubService clubService) {
        this.clubService = clubService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Spring Security appelle cette méthode au moment de l'authentification.
        var membre = clubService.findMembreByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Membre introuvable"));

        // On transforme notre objet métier Membre en objet UserDetails compréhensible
        // par Spring Security : identifiant, mot de passe encodé et rôle.
        return User.withUsername(membre.getEmail())
                .password(membre.getMotDePasse())
                .authorities(List.of(new SimpleGrantedAuthority("ROLE_MEMBER")))
                .build();
    }
}
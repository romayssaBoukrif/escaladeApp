package myapp.service;

import myapp.dao.ClubDao;
import myapp.model.PasswordResetToken;
import myapp.repo.PasswordResetTokenRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class PasswordResetService {

    private final ClubDao clubDao;
    private final PasswordResetTokenRepository tokenRepository;
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;

    public PasswordResetService(ClubDao clubDao,
                                PasswordResetTokenRepository tokenRepository,
                                JavaMailSender mailSender,
                                PasswordEncoder passwordEncoder) {
        this.clubDao = clubDao;
        this.tokenRepository = tokenRepository;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
    }

    public void requestReset(String email) {
        var membre = clubDao.findMembreByEmail(email).orElseThrow();

        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken(
                token,
                LocalDateTime.now().plusHours(1),
                membre
        );
        tokenRepository.save(resetToken);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Réinitialisation du mot de passe");
        message.setText("Lien : http://localhost:8080/reset-password?token=" + token);
        mailSender.send(message);
    }

    public void resetPassword(String token, String nouveauMotDePasse) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token).orElseThrow();

        if (resetToken.isExpired()) {
            throw new IllegalStateException("Token expiré");
        }

        var membre = resetToken.getMembre();
        membre.setMotDePasse(passwordEncoder.encode(nouveauMotDePasse));
        clubDao.saveMember(membre);

        tokenRepository.delete(resetToken);
    }
}
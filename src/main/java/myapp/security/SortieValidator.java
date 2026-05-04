package myapp.security;


import myapp.web.form.SortieForm;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Service
public class SortieValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SortieForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SortieForm form = (SortieForm) target;

        if (form.getDateSortie() != null && form.getDateSortie().isBefore(LocalDate.now())) {
            errors.rejectValue("dateSortie", "date.passee",
                    "La date doit être aujourd’hui ou dans le futur.");
        }

        if (form.getSiteWeb() != null && !form.getSiteWeb().isBlank()) {
            if (!form.getSiteWeb().startsWith("http://") && !form.getSiteWeb().startsWith("https://")) {
                errors.rejectValue("siteWeb", "siteweb.invalide",
                        "Le site web doit commencer par http:// ou https://");
            }
        }
    }
}
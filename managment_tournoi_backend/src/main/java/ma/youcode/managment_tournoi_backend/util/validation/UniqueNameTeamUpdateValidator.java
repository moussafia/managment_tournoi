package ma.youcode.managment_tournoi_backend.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.repository.TeamRepository;
import org.springframework.beans.BeanWrapperImpl;
@RequiredArgsConstructor
public class UniqueNameTeamUpdateValidator implements ConstraintValidator<UniqueNameTeamUpdate, Object> {
    private final TeamRepository teamRepository;
    private String nameOfTeam;
    private String userId;
    private String message;

    @Override
    public void initialize(UniqueNameTeamUpdate constraintAnnotation) {
        userId = constraintAnnotation.userId();
        nameOfTeam = constraintAnnotation.nameOfTeam();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object nameTeamO = new BeanWrapperImpl(value).getPropertyValue(nameOfTeam);

        Object userIdO = new BeanWrapperImpl(value).getPropertyValue(userId);
        Team team;
        boolean isValid = true;
        if (nameTeamO != null && userIdO != null) {
            team = teamRepository.findByNameTeam(nameTeamO.toString()).orElse(null);
            if (team != null) {
                isValid = userIdO.equals(team.getId());
            }
        }

        if (!isValid) {

            context.disableDefaultConstraintViolation();

            context

                    .buildConstraintViolationWithTemplate(message)

                    .addPropertyNode(nameOfTeam)

                    .addConstraintViolation();

        }


        return isValid;
    }
}

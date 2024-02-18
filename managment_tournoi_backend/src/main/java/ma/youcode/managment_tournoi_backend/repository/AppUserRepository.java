package ma.youcode.managment_tournoi_backend.repository;

import ma.youcode.managment_tournoi_backend.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsername(String email);
}

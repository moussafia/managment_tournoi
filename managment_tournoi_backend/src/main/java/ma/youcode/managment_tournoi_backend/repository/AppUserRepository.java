package ma.youcode.managment_tournoi_backend.repository;

import ma.youcode.managment_tournoi_backend.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUsername(String email);
    Page<AppUser> findAll(Pageable pageable);
    @Query("SELECT u FROM AppUser u WHERE u.firstName LIKE %:keyword% OR u.lastName LIKE %:keyword% ORDER BY u.createdAt DESC")
    Optional<List<AppUser>> findFirstUserByFirstNameOrLastNameOrderByDateOfCreation(@Param("keyword") String keyWord, Pageable pageable);

}

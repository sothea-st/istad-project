package co.istad.mbanking.features.user;

import co.istad.mbanking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByPhoneNumberAndIsDeletedFalse(String phoneNumber);
    // SELECT * FROM users where uuid = ?
    Optional<User> findByUuid(String uuid);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    boolean existsByNationalCardId(String nationalId);

    Optional<User> findByEmail(String email);

    

}

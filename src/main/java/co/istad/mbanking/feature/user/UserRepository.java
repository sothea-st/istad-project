package co.istad.mbanking.feature.user;

import co.istad.mbanking.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUuid(String uuid);
}

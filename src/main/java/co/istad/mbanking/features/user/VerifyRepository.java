package co.istad.mbanking.features.user;

import org.springframework.data.jpa.repository.JpaRepository;

import co.istad.mbanking.domain.User;
import co.istad.mbanking.domain.Verify;
import java.util.Optional;
public interface VerifyRepository extends JpaRepository<Verify,Integer> {
    Optional<Verify> findByUserAndDigit(User user , String digit);

    Optional<Verify> findByUser(User user);
}

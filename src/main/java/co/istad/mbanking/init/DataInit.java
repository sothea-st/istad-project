package co.istad.mbanking.init;

import co.istad.mbanking.domain.AccountType;
import co.istad.mbanking.domain.Role;
import co.istad.mbanking.domain.User;
import co.istad.mbanking.features.accounttype.AccountTypeRepository;
import co.istad.mbanking.features.user.RoleRepository;
import co.istad.mbanking.features.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final UserRepository userRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {

        if (accountTypeRepository.count() == 0) {
            AccountType payroll = new AccountType();
            payroll.setName("Payroll");
            payroll.setAlias("payroll");
            payroll.setIsDeleted(false);
            payroll.setDescription("Payroll Account of User");

            AccountType saving = new AccountType();
            saving.setName("Saving");
            saving.setAlias("saving");
            saving.setIsDeleted(false);
            saving.setDescription("Saving Account of User");

            accountTypeRepository.save(payroll);
            accountTypeRepository.save(saving);
        }

        if (userRepository.count() == 0) {

            Role userRole = new Role();
            userRole.setName("USER");

            Role customer = new Role();
            customer.setName("CUSTOMER");

            Role admin = new Role();
            admin.setName("ADMIN");

            Role manager = new Role();
            manager.setName("MANAGER");

            roleRepository.saveAll(List.of(userRole,customer,admin,manager));

            User user = new User();
            user.setUuid(UUID.randomUUID().toString());
            user.setName("Chan Chhaya");
            user.setGender("Male");
            user.setPhoneNumber("098459947");
            user.setEmail("hello123@gmail.com");
            user.setPin("1234");
            user.setPassword(passwordEncoder.encode("qwer"));
            user.setNationalCardId("123456789");
            user.setProfileImage("user/avatar.png");
            user.setStudentCardId("ISTAD-000001");
            user.setIsDeleted(false);
            user.setIsBlocked(false);
            user.setRoles(List.of(userRole,customer));

            User user2 = new User();
            user2.setUuid(UUID.randomUUID().toString());
            user2.setName("Leo Messi");
            user2.setGender("Male");
            user2.setPhoneNumber("077459947");
            user2.setEmail("java@gmail.com");
            user2.setPin("1234");
            user2.setPassword(passwordEncoder.encode("qwer"));
            user2.setNationalCardId("88889999");
            user2.setProfileImage("user/avatar.png");
            user2.setIsDeleted(false);
            user2.setIsBlocked(false);
            user2.setRoles(List.of(userRole,admin));


            User user3 = new User();
            user3.setUuid(UUID.randomUUID().toString());
            user3.setName("CR7");
            user3.setGender("Male");
            user3.setPhoneNumber("077459999");
            user3.setEmail("spring@gmail.com");
            user3.setPin("1432");
            user3.setPassword(passwordEncoder.encode("qwer"));
            user3.setNationalCardId("88880000");
            user3.setProfileImage("user/avatar.png");
            user3.setIsDeleted(false);
            user3.setIsBlocked(false);
            user3.setRoles(List.of(userRole,manager));

            //userRepository.save(user);
            //userRepository.save(user2);
            userRepository.saveAll(List.of(user, user2,user3));
        }

    }

}

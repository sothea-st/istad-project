package co.istad.mbanking.init;

import co.istad.mbanking.domain.AccountType;
import co.istad.mbanking.domain.User;
import co.istad.mbanking.feature.accounttype.AccountTypeRepository;
import co.istad.mbanking.feature.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitData {
    private final UserRepository userRepository;
    private final AccountTypeRepository accountTypeRepository;

    @PostConstruct
    void initData(){


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

        if( userRepository.count() == 0 ) {

            User user = new User();
            user.setName("Hello");
            user.setUuid(UUID.randomUUID().toString());
            user.setGender("male");
            user.setPhoneNumber("098989890");
            user.setIsDeleted(false);
            user.setPassword("qwer");
            user.setIsBlocked(false);
            user.setPin("1234");
            user.setNationalCardId("99998888");

            User user2 = new User();
            user2.setName("Hello 2");
            user2.setUuid(UUID.randomUUID().toString());
            user2.setGender("male");
            user2.setPhoneNumber("090909090");
            user2.setIsDeleted(false);
            user2.setIsBlocked(false);
            user2.setPassword("qwer");
            user2.setPin("1234");
            user2.setNationalCardId("87778888");

            userRepository.saveAll(List.of(user,user2));
        }

    }

}

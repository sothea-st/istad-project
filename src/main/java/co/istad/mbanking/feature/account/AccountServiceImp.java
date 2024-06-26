package co.istad.mbanking.feature.account;

import co.istad.mbanking.domain.Account;
import co.istad.mbanking.domain.AccountType;
import co.istad.mbanking.domain.User;
import co.istad.mbanking.feature.account.dto.AccountCreateRequest;
import co.istad.mbanking.feature.account.dto.AccountResponse;
import co.istad.mbanking.feature.accounttype.AccountTypeRepository;
import co.istad.mbanking.feature.user.UserRepository;
import co.istad.mbanking.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService{
    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponse create(AccountCreateRequest accountCreateRequest) {

        if( accountRepository.existsByActNo(accountCreateRequest.actNo()) ){
            throw  new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Account number is already exists!"
            );
        }

        if( accountCreateRequest.balance().doubleValue() < 10 ) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Balance 10$ is required to create account !"
            );
        }

        AccountType accountType = accountTypeRepository.findByAlias(accountCreateRequest.accountTypeAlias())
                .orElseThrow(()->new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Account type  not found !."
                ));

        User user = userRepository.findByUuid(accountCreateRequest.userUuid())
                .orElseThrow(
                        ()->new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "UUID not found !."
                        )
                );

        Account account = accountMapper.toAccount(accountCreateRequest);

        account.setAccountType(accountType);
        account.setUser(user);
        account.setActName(user.getName());
        account.setIsHidden(false);
        account.setTransferLimit(BigDecimal.valueOf(1000));
        account = accountRepository.save(account);

        return accountMapper.toAccountResponse(account);

    }
}

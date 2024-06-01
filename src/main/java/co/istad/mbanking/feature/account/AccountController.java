package co.istad.mbanking.feature.account;

import co.istad.mbanking.feature.account.dto.AccountCreateRequest;
import co.istad.mbanking.feature.account.dto.AccountResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    AccountResponse create(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
        return accountService.create(accountCreateRequest);
    }
}

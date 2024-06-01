package co.istad.mbanking.feature.account;

import co.istad.mbanking.feature.account.dto.AccountCreateRequest;
import co.istad.mbanking.feature.account.dto.AccountResponse;

public interface AccountService {

    // create new

    AccountResponse create(AccountCreateRequest accountCreateRequest);

}

package co.istad.mbanking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.istad.mbanking.domain.Account;
import co.istad.mbanking.feature.account.dto.AccountCreateRequest;
import co.istad.mbanking.feature.account.dto.AccountResponse;

@Mapper(componentModel = "spring")
public interface AccountMapper {
     // Map Account to AccountResponse
     // Source = Account
     // Target = AccountResponse
     @Mapping(target = "accountTypeAlias" ,source = "accountType.alias")
     AccountResponse toAccountResponse(Account account);

     Account toAccount(AccountCreateRequest accountCreateRequest);
}

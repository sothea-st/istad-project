package co.istad.mbanking.mapper;

import co.istad.mbanking.domain.Account;
import co.istad.mbanking.features.account.dto.AccountCreateRequest;
import co.istad.mbanking.features.account.dto.AccountResponse;
import co.istad.mbanking.features.account.dto.AccountUpdateRequest;
import co.istad.mbanking.features.account.dto.AccountUpdateResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    // Map Account to AccountResponse
    // Source = Account
    // Target = AccountResponse
    // @Mapping(source = "accountType.alias", target = "accountTypeAlias")
    AccountResponse toAccountResponse(Account account);

    Account fromAccountCreateRequest(AccountCreateRequest accountCreateRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromAccountUpdateRequest(AccountUpdateRequest accountUpdateRequest,
            @MappingTarget Account account);

    AccountUpdateResponse toAccountUpdateResponse(Account account);

}

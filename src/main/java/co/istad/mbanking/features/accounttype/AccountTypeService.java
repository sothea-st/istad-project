package co.istad.mbanking.features.accounttype;

import co.istad.mbanking.features.accounttype.dto.AccountTypeRequest;
import co.istad.mbanking.features.accounttype.dto.AccountTypeResponse;
import co.istad.mbanking.features.accounttype.dto.AccountTypeUpdateRequest;

import java.util.List;

import org.springframework.data.domain.Page;

public interface AccountTypeService {

    AccountTypeResponse findByAlias(String alias);

    void deleteByAlias(String alias);

    AccountTypeResponse updateByAlias(String alias, AccountTypeUpdateRequest accountTypeUpdateRequest);

    void createNew(AccountTypeRequest accountTypeRequest);

    Page<AccountTypeResponse> findList(int pageNumber , int pageSize);

}

package co.istad.mbanking.mapper;

import org.mapstruct.Mapper;
 

import co.istad.mbanking.domain.AccountType;
import co.istad.mbanking.feature.accounttype.dto.AccountTypeRequest;
import co.istad.mbanking.feature.accounttype.dto.AccountTypeResponse;
import java.util.*;
@Mapper(componentModel = "spring")
public interface AccountTypeMapper {

     // source = AccountTypeRequest
     // target = AccountType
     AccountType toAccountType(AccountTypeRequest accountTypeRequest);

   
     AccountTypeResponse toAccountTypeResponse(AccountType accountType);

     List<AccountTypeResponse> toListAccountTypeResponse(List<AccountType> accountTypes);

}

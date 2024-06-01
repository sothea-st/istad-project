package co.istad.mbanking.feature.accounttype;

import co.istad.mbanking.feature.accounttype.dto.AccountTypeRequest;
import co.istad.mbanking.feature.accounttype.dto.AccountTypeResponse;
import java.util.*;
public interface AccountTypeService {
     
     // create 
     AccountTypeResponse create(AccountTypeRequest accountTypeRequest);

     // retrieve by alias
     AccountTypeResponse retrieve(Integer id);

     // retrieve all
     List<AccountTypeResponse> retrieve();

     // update
     AccountTypeResponse update(Integer id , AccountTypeRequest accountTypeRequest);

     // delete
     void delete(Integer id);

     
}  

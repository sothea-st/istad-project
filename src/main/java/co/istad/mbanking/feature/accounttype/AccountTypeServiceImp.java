package co.istad.mbanking.feature.accounttype;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.istad.mbanking.domain.AccountType;
import co.istad.mbanking.feature.accounttype.dto.AccountTypeRequest;
import co.istad.mbanking.feature.accounttype.dto.AccountTypeResponse;
import co.istad.mbanking.mapper.AccountTypeMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountTypeServiceImp implements AccountTypeService {
     private final AccountTypeRepository accountTypeRepository;
     private final AccountTypeMapper accountTypeMapper;

     private void alreadyExistsFun(String value) {
          if (accountTypeRepository.existsByName(value))
               throw new ResponseStatusException(
                         HttpStatus.CONFLICT,
                         "Account name already token !");
     }

     private AccountType dataNotFound(Integer id) {
          return accountTypeRepository.findById(id)
                    .orElseThrow(
                              () -> new ResponseStatusException(
                                        HttpStatus.NOT_FOUND,
                                        "Id does not exist ."));
     }

     @Override
     public AccountTypeResponse create(AccountTypeRequest accountTypeRequest) {
          alreadyExistsFun(accountTypeRequest.name());
          AccountType accountType = accountTypeMapper.toAccountType(accountTypeRequest);
          accountType.setAlias(UUID.randomUUID().toString());
          accountType.setIsDeleted(false);
          accountTypeRepository.save(accountType);
          return accountTypeMapper.toAccountTypeResponse(accountType);
     }

     @Override
     public AccountTypeResponse retrieve(Integer id) {
          AccountType accountType = dataNotFound(id);
          return accountTypeMapper.toAccountTypeResponse(accountType);
     }

     @Override
     public List<AccountTypeResponse> retrieve() {
          List<AccountType> accountTypes = accountTypeRepository.findAll();
          return accountTypeMapper.toListAccountTypeResponse(accountTypes);
     }

     @Override
     public AccountTypeResponse update(Integer id, AccountTypeRequest accountTypeRequest) {
          alreadyExistsFun(accountTypeRequest.name());
          AccountType accountType = dataNotFound(id);
          accountType.setName(accountTypeRequest.name());
          accountType.setDescription(accountTypeRequest.description());
          accountTypeRepository.save(accountType);
          return accountTypeMapper.toAccountTypeResponse(accountType);

     }

     @Override
     public void delete(Integer id) {
          dataNotFound(id);
          accountTypeRepository.deleteById(id);
     }

}

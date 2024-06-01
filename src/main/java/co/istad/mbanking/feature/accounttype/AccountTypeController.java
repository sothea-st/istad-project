package co.istad.mbanking.feature.accounttype;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.istad.mbanking.feature.accounttype.dto.AccountTypeRequest;
import co.istad.mbanking.feature.accounttype.dto.AccountTypeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.*;


@RestController
@RequestMapping("/api/v1/accountTypes")
@RequiredArgsConstructor
public class AccountTypeController {
     private final AccountTypeService accountTypeService;

     @PostMapping
     public AccountTypeResponse create(@Valid @RequestBody AccountTypeRequest accountTypeRequest) {
          return accountTypeService.create(accountTypeRequest);
     }

     @GetMapping("/{id}")
     public AccountTypeResponse retrieve(@PathVariable("id") Integer id) {
          return accountTypeService.retrieve(id);
     }

     @GetMapping
     public List<AccountTypeResponse> retrieve(){
          return accountTypeService.retrieve();
     }

     @PutMapping("/{id}")
     public AccountTypeResponse update(@PathVariable("id") Integer id , @RequestBody AccountTypeRequest accountTypeRequest) {
          return accountTypeService.update(id, accountTypeRequest);
     }

     @DeleteMapping("/{id}")
     @ResponseStatus(HttpStatus.OK)
     public void delete(@PathVariable("id") Integer id) {
          accountTypeService.delete(id);
     }
     

}

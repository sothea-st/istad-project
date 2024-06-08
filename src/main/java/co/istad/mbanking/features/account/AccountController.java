package co.istad.mbanking.features.account;

import co.istad.mbanking.features.account.dto.AccountCreateRequest;
import co.istad.mbanking.features.account.dto.AccountRenameRequest;
import co.istad.mbanking.features.account.dto.AccountResponse;
import co.istad.mbanking.features.account.dto.AccountTransferLimitRequest;
import co.istad.mbanking.features.account.dto.AccountUpdateRequest;
import co.istad.mbanking.features.account.dto.AccountUpdateResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;


    @PatchMapping("/{actNo}/update")
    public AccountUpdateResponse update( @PathVariable("actNo") String actNo ,@RequestBody  AccountUpdateRequest accountUpdateRequest) {
        return accountService.update(actNo, accountUpdateRequest);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{actNo}/transfer-limit")
    void updateTransferLimit(@PathVariable("actNo") String actNo,
                     @Valid @RequestBody AccountTransferLimitRequest accountTransferLimitRequest) {
        accountService.updateTransferLimit(actNo, accountTransferLimitRequest);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{actNo}/hide")
    void hideAccount(@PathVariable("actNo") String actNo) {
        accountService.hideAccount(actNo);
    }


    @PutMapping("/{actNo}/rename")
    AccountResponse renameAccount(@PathVariable("actNo") String actNo,
                                  @Valid @RequestBody AccountRenameRequest accountRenameRequest) {
        return accountService.renameAccount(actNo, accountRenameRequest);
    }


    @GetMapping("/{actNo}")
    AccountResponse findByActNo(@PathVariable("actNo") String actNo) {
        return accountService.findByActNo(actNo);
    }


    @GetMapping
    Page<AccountResponse> findList(
            @RequestParam(name = "pageNumber" , required = false , defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize" , required =  false , defaultValue = "10") int pageSize
    ) {
        return accountService.findList(pageNumber, pageSize);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    AccountResponse createNew(@Valid @RequestBody AccountCreateRequest accountCreateRequest) {
        return accountService.createNew(accountCreateRequest);
    }


    @PutMapping("/{actNo}/delete-soft")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteSoft(@PathVariable("actNo") String actNo){
        accountService.deleteByActNo(actNo);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{actNo}")
    void deleteHard(@PathVariable("actNo") String actNo){
        accountService.deleteAccount(actNo);
    }

}

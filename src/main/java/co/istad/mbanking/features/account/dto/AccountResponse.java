package co.istad.mbanking.features.account.dto;

import co.istad.mbanking.features.accounttype.dto.AccountTypeResponse;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountResponse(
        String alias,
        String actName,
        String actNo,
        BigDecimal balance,
        AccountTypeResponse accountType ,
        Boolean isDeleted
) {
}

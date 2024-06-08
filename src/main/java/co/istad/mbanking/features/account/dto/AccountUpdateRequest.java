package co.istad.mbanking.features.account.dto;

import co.istad.mbanking.domain.AccountType;

public record AccountUpdateRequest(
     String actName,
     AccountType accountType 
) {
     
}

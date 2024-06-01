package co.istad.mbanking.feature.accounttype.dto;

import jakarta.validation.constraints.NotBlank;

public record AccountTypeRequest(
 
     @NotBlank(message = "the field name is required !")
     String name ,

     String description
     
) {
     
}

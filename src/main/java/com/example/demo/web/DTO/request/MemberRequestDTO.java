package com.example.demo.web.DTO.request;

import com.example.demo.entity.Member;
import com.example.demo.enums.IdentityDocumentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MemberRequestDTO(
    @NotNull(message = "first name is required")
    @Size(min = 2, max = 50, message = "first name must be between 2 and 50 characters")
    String firstName,

    @NotNull(message = "Family name is required")
    @Size(min = 2, max = 50, message = "Family name must be between 2 and 50 characters")
    String familyName,

    @NotNull(message = "Nationality is required")
    @Size(min = 2, max = 50, message = "Nationality must be between 2 and 50 characters")
    String nationality,

    @NotNull(message = "Nationality is required")
    String identityDocumentType
) {
    public Member toMember(){
        return Member.builder()
                .familyName(familyName)
                .firstName(firstName)
                .nationality(nationality)
                .identityDocumentType(IdentityDocumentType.valueOf(identityDocumentType))
                .build();
    }
}

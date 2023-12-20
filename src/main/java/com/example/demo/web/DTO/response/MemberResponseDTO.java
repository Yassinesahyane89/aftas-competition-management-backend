package com.example.demo.web.DTO.response;

import com.example.demo.entity.Member;

import java.time.LocalDate;

public record MemberResponseDTO(
        Long membershipNumber,
        String FullName,
        String nationality,
        LocalDate accessDate,
        String identityDocumentType

) {
       public static MemberResponseDTO fromMember(Member member) {
            return new MemberResponseDTO(
                    member.getMembershipNumber(),
                    member.getFirstName() + " " + member.getFamilyName(),
                    member.getNationality(),
                    member.getAccessDate(),
                    member.getIdentityDocumentType().toString()
            );
        }
}

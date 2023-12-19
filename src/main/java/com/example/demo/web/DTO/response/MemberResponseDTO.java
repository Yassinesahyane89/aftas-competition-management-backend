package com.example.demo.web.DTO.response;

import com.example.demo.entity.Member;

import java.time.LocalDate;

public record MemberResponseDTO(
        Long id,
        String FullName,
        String nationality,
        LocalDate accessDate,
        String IdentityDocumentType,
        String membershipNumber

) {
       public static MemberResponseDTO fromMember(Member member) {
            return new MemberResponseDTO(
                    member.getId(),
                    member.getFirstName() + " " + member.getFamilyName(),
                    member.getNationality(),
                    member.getAccessDate(),
                    member.getIdentityDocumentType().toString(),
                    member.getMembershipNumber() + ""
            );
        }
}

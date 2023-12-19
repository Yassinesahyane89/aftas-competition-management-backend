package com.example.demo.service;

import com.example.demo.entity.Member;

import java.util.List;

public interface MemberService {
    Member getMemberById(Long id);
    List<Member> getAllMembers();
    List<Member> findByFirstNameOrMembershipNumberOrFamilyName(String searchTerm);
    Member addMember(Member member);
    Member updateMember(Member member, Long id);
    void deleteMember(Long id);
}

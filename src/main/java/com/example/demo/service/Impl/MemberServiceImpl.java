package com.example.demo.service.Impl;

import com.example.demo.entity.Member;
import com.example.demo.handler.exception.ResourceNotFountException;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new ResourceNotFountException("Member id " + id + " not found"));
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public List<Member> getMembersByCompetitionCode(String code) {
        List<Member> members = memberRepository.findAllByCompetitionCode(code);
        if(members == null){
            throw new ResourceNotFountException("There is no members in competition code " + code);
        }
        return members;
    }

    @Override
    public List<Member> getMembersNotInCompetition(String code) {
        return null;
    }

    @Override
    public List<Member> findByFirstNameOrMembershipNumberOrFamilyName(String searchTerm) {
        return memberRepository.findByMembershipNumberOrFirstNameOrFamilyName(searchTerm);
    }

    @Override
    public Member addMember(Member member) {
        // add access date and it's date of today
        member.setAccessDate(LocalDate.now());

        // add identity number do UUId
        member.setIdentityNumber(java.util.UUID.randomUUID().toString());

        // add membership number integer and must be unique
        member.setMembershipNumber((int) (memberRepository.count() + 1));

        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(Member member, Long id) {
        // check if member is exist
        Member existingMember = getMemberById(id);

        // update member
        existingMember.setFirstName(member.getFirstName());
        existingMember.setFamilyName(member.getFamilyName());
        existingMember.setNationality(member.getNationality());
        existingMember.setIdentityDocumentType(member.getIdentityDocumentType());

        // save member
        return memberRepository.save(existingMember);
    }

    @Override
    public void deleteMember(Long id) {
        // check if member is exist
        getMemberById(id);

        // delete member
        memberRepository.deleteById(id);
    }
}

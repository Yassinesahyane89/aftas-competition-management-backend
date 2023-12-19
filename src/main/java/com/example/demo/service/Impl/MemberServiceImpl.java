package com.example.demo.service.Impl;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public Member getMemberById(Long id) {
        return null;
    }

    @Override
    public List<Member> getAllMembers() {
        return null;
    }

    @Override
    public List<Member> findByFirstNameOrMembershipNumberOrFamilyName(String searchTerm) {
        return null;
    }

    @Override
    public Member addMember(Member member) {
        return null;
    }

    @Override
    public Member updateMember(Member member, Long id) {
        return null;
    }

    @Override
    public void deleteMember(Long id) {

    }
}

package com.example.demo.web.rest.controller;

import com.example.demo.entity.Member;
import com.example.demo.handler.response.ResponseMessage;
import com.example.demo.service.MemberService;
import com.example.demo.web.DTO.response.MemberResponseDTO;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        if(members.isEmpty()) {
            return ResponseMessage.notFound("Member not found");
        }else {
            List<MemberResponseDTO> memberResponseDTOS = new ArrayList<>();
            members.forEach(member -> {
                memberResponseDTOS.add(MemberResponseDTO.fromMember(member));
            });
            return ResponseMessage.ok(memberResponseDTOS, "Success");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getMemberById(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        if(member == null) {
            return ResponseMessage.notFound("Member not found");
        }else {
            return ResponseMessage.ok(MemberResponseDTO.fromMember(member), "Success");
        }
    }
}

package com.example.demo.web.rest.controller;

import com.example.demo.entity.Member;
import com.example.demo.handler.response.ResponseMessage;
import com.example.demo.service.MemberService;
import com.example.demo.web.DTO.request.MemberRequestDTO;
import com.example.demo.web.DTO.response.MemberResponseDTO;
import jakarta.validation.Valid;
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

    @GetMapping("/search")
    public ResponseEntity searchMember(@RequestBody String searchTerm) {
        List<Member> members = memberService.findByFirstNameOrMembershipNumberOrFamilyName(searchTerm);
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

    @PostMapping
    public ResponseEntity addMember(@Valid @RequestBody MemberRequestDTO memberRequestDTO) {
        Member member1 = memberService.addMember(memberRequestDTO.toMember());
        if(member1 == null) {
            return ResponseMessage.badRequest("Member not created");
        }else {
            return ResponseMessage.created(MemberResponseDTO.fromMember(member1), "Member created successfully");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMember(@Valid @RequestBody MemberRequestDTO memberRequestDTO, @PathVariable Long id) {
        Member member1 = memberService.updateMember(memberRequestDTO.toMember(), id);
        if(member1 == null) {
            return ResponseMessage.badRequest("Member not updated");
        }else {
            return ResponseMessage.ok(MemberResponseDTO.fromMember(member1), "Member updated successfully");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseMessage.ok(null,"Member deleted successfully");
    }

    //get all members that are not in competition
    @GetMapping("/not-in-competition/{code}")
    public ResponseEntity getMembersNotInCompetition(@PathVariable String code) {
        List<Member> members = memberService.getMembersNotInCompetition(code);
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

    //get all members that are in competition
    @GetMapping("/in-competition/{code}")
    public ResponseEntity getMembersInCompetition(@PathVariable String code) {
        List<Member> members = memberService.getMembersByCompetitionCode(code);
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
}

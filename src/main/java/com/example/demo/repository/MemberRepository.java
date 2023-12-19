package com.example.demo.repository;

import com.example.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query( value =
            "SELECT * FROM member WHERE membership_number = :searchTerm " +
            "OR first_name LIKE %:searchTerm% OR family_name LIKE %:searchTerm%", nativeQuery = true)
    List<Member> findByMembershipNumberOrFirstNameOrFamilyName(@Param("searchTerm") String searchTerm);
}

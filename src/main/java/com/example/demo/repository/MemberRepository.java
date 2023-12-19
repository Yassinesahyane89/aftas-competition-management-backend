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

    // get all members that are in a competition
    @Query( value =
            "SELECT * FROM member WHERE membership_number IN " +
            "(SELECT member_membership_number FROM ranking WHERE competition_code = :competitionCode)", nativeQuery = true)
    List<Member> findAllByCompetitionCode(@Param("competitionCode") String competitionCode);

    // get all members that are not in a competition
    @Query( value =
            "SELECT * FROM member WHERE membership_number NOT IN " +
            "(SELECT member_membership_number FROM ranking WHERE competition_code = :competitionCode)", nativeQuery = true)
    List<Member> findAllNotInCompetition(@Param("competitionCode") String competitionCode);

}

package com.example.demo.repository;

import com.example.demo.entity.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HuntingRepository extends JpaRepository<Hunting, Long> {
}

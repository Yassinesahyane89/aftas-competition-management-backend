package com.example.demo.repository;

import com.example.demo.entity.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish, Long> {
    Fish findByName(String name);
}

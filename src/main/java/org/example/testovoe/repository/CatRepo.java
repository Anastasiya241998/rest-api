package org.example.testovoe.repository;

import org.example.testovoe.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CatRepo extends JpaRepository<Cat, Integer> {
}

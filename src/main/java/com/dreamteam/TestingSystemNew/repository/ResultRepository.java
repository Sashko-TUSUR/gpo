package com.dreamteam.TestingSystemNew.repository;

import com.dreamteam.TestingSystemNew.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
}

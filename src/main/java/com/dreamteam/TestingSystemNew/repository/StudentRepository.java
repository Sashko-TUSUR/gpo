package com.dreamteam.TestingSystemNew.repository;

import com.dreamteam.TestingSystemNew.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {
}

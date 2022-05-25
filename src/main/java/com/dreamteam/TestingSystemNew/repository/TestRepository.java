package com.dreamteam.TestingSystemNew.repository;

import com.dreamteam.TestingSystemNew.model.Test;
import com.dreamteam.TestingSystemNew.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    Test getByTopic(Topic topic);
}

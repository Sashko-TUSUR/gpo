package com.dreamteam.TestingSystemNew.repository;

import com.dreamteam.TestingSystemNew.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    //Optional<Topic> findByTopicId(Long id);
}

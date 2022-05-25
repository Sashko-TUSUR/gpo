package com.dreamteam.TestingSystemNew.repository;

import com.dreamteam.TestingSystemNew.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupsRepository extends JpaRepository<Groups, Long> {
    Groups getByName(String name);
    Groups getById(Long id);
}

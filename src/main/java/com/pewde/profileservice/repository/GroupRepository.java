package com.pewde.profileservice.repository;

import com.pewde.profileservice.entity.Group;
import com.pewde.profileservice.entity.Wall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    Optional<Group> findByWall(Wall wall);

}

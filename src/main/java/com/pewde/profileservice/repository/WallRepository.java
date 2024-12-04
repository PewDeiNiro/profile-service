package com.pewde.profileservice.repository;

import com.pewde.profileservice.entity.Wall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WallRepository extends JpaRepository<Wall, Integer> {

    Optional<Wall> findById(int id);

}

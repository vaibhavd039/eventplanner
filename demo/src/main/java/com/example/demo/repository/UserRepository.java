package com.example.demo.repository;

import com.example.demo.domain.UserEntity;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Set<UserEntity> findUserEntitiesByIdIn(List<Long> ids);
}

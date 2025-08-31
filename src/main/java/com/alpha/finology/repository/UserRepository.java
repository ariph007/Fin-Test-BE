package com.alpha.finology.repository;

import com.alpha.finology.entity.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {


  Page<User> findAll(Pageable pageable);

  Optional<User> findById(String id);

  Optional<User> findByUsername(String username);


}
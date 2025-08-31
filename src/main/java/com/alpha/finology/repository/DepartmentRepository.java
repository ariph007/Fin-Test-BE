package com.alpha.finology.repository;

import com.alpha.finology.entity.Department;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String>,
    JpaSpecificationExecutor<Department> {
  Page<Department> findAll(Pageable pageable);

  Optional<Department> findById(String id);

  boolean existsByCode(String code);
  boolean existsById(String id);

  void deleteById(String s);
}

package com.alpha.finology.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface MasterBaseRepository<T, ID> extends JpaRepository<T, ID>,
    JpaSpecificationExecutor<T> {
  Page<T> findAll(Pageable pageable);

  Optional<T> findById(ID id);

  boolean existsById(ID id);

  void deleteById(ID id);

  boolean existsByCode(String code);
}

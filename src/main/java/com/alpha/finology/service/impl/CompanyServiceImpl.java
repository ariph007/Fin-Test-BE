package com.alpha.finology.service.impl;

import com.alpha.finology.entity.Company;
import com.alpha.finology.repository.CompanyRepository;
import com.alpha.finology.service.CompanyService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

  private final CompanyRepository repository;


  @Override
  public List<Company> getAllCompanies() {
    return repository.findAll();
  }

  @Override
  public void createCompany(Company company) {
    repository.save(company);
  }

  @Override
  public List<Company> bulkCreateCompany(List<Company> companies) {
    return repository.saveAll(companies);
  }

}

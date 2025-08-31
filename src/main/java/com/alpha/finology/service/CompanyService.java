package com.alpha.finology.service;

import com.alpha.finology.entity.Company;
import com.alpha.finology.model.request.PagingRequest;
import com.alpha.finology.model.response.UserResponse;
import java.util.List;
import org.springframework.data.domain.Page;

public interface CompanyService {
  List<Company> getAllCompanies();

  void createCompany(Company company);
  List<Company> bulkCreateCompany(List<Company> companies);
}

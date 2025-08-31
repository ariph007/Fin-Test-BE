package com.alpha.finology.service;

import com.alpha.finology.entity.Address;
import com.alpha.finology.entity.Company;
import java.util.List;

public interface AddressService {
  List<Address> getAllCompanies();

  void createCompany(Address address);

  List<Address> bulkCreateAddress(List<Address> addresses);
}

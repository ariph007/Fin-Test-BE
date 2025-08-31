package com.alpha.finology.service.impl;

import com.alpha.finology.entity.Address;
import com.alpha.finology.repository.AddressRepository;
import com.alpha.finology.service.AddressService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

  private final AddressRepository repository;


  @Override
  public List<Address> getAllCompanies() {
    return repository.findAll();
  }

  @Override
  public void createCompany(Address address) {
    repository.save(address);
  }

  @Override
  public List<Address> bulkCreateAddress(List<Address> addresses) {
    return repository.saveAll(addresses);
  }


}

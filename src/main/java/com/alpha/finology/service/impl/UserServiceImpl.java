package com.alpha.finology.service.impl;

import com.alpha.finology.entity.Address;
import com.alpha.finology.entity.Company;
import com.alpha.finology.entity.Geo;
import com.alpha.finology.entity.User;
import com.alpha.finology.helper.SpecificationHelper;
import com.alpha.finology.model.request.PagingRequest;
import com.alpha.finology.model.response.AddressResponse;
import com.alpha.finology.model.response.CompanyResponse;
import com.alpha.finology.model.response.GeoResponse;
import com.alpha.finology.model.response.UserResponse;
import com.alpha.finology.repository.UserRepository;
import com.alpha.finology.service.AddressService;
import com.alpha.finology.service.CompanyService;
import com.alpha.finology.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;
  //  private final WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com/users");

  private final CompanyService companyService;
  private final AddressService addressService;
  private final WebClient.Builder webClientBuilder;

  @Value("${finology.typicode.base-url}")
  private String baseUrl;


  @PostConstruct
  @SneakyThrows
  public void init() {
    if (repository.count() == 0) {
      WebClient webClient = webClientBuilder.baseUrl(baseUrl).build();
      String res = webClient.get()
          .uri("/users")
          .retrieve()
          .bodyToMono(String.class)
          .block();

      ObjectMapper mapper = new ObjectMapper();
      List<UserResponse> users = mapper.readValue(res, new TypeReference<List<UserResponse>>() {
      });

      // prepare companies
      List<Company> companies = users.stream().map(user -> {
        Company company = new Company();
        company.setName(user.getCompany().getName());
        company.setCatchPhrase(user.getCompany().getCatchPhrase());
        company.setBs(user.getCompany().getBs());
        company.setCreatedAt(java.time.ZonedDateTime.now());
        company.setUpdatedAt(java.time.ZonedDateTime.now());
        company.setCreatedBy("system");
        company.setUpdatedBy("system");
        return company;
      }).toList();

      // prepare addresses
      List<Address> addresses = users.stream().map(user -> {
        Address address = new Address();
        address.setStreet(user.getAddress().getStreet());
        address.setSuite(user.getAddress().getSuite());
        address.setCity(user.getAddress().getCity());
        address.setZipcode(user.getAddress().getZipcode());
        address.setCreatedAt(java.time.ZonedDateTime.now());
        address.setUpdatedAt(java.time.ZonedDateTime.now());
        address.setCreatedBy("system");
        address.setUpdatedBy("system");

        Geo geo = new Geo();
        geo.setLat(user.getAddress().getGeo().getLat());
        geo.setLng(user.getAddress().getGeo().getLng());
        address.setGeo(geo);

        return address;
      }).toList();

      companyService.bulkCreateCompany(companies);
      addressService.bulkCreateAddress(addresses);

      List<Company> savedCompanies = companyService.getAllCompanies();
      List<Address> savedAddresses = addressService.getAllCompanies();

      for (UserResponse user : users) {
        User entity = new User();
        BeanUtils.copyProperties(user, entity, "id", "version");

        Company company = savedCompanies.stream()
            .filter(c -> c.getName().equals(user.getCompany().getName()))
            .findFirst()
            .orElse(null);

        Address address = savedAddresses.stream()
            .filter(a -> a.getStreet().equals(user.getAddress().getStreet())
                         && a.getCity().equals(user.getAddress().getCity()))
            .findFirst()
            .orElse(null);

        entity.setCompany(company);
        entity.setAddress(address);

        entity.setCreatedAt(java.time.ZonedDateTime.now());
        entity.setUpdatedAt(java.time.ZonedDateTime.now());
        entity.setCreatedBy("system");
        entity.setUpdatedBy("system");

        repository.save(entity);
      }
    }
  }


  @Override
  public Page<UserResponse> getAll(PagingRequest pagingRequest, String inquiry) {

    PageRequest pageRequest = PageRequest.of(pagingRequest.getPage(), pagingRequest.getPageSize(),
        SpecificationHelper.createSort(pagingRequest.getSortBy()));

    Specification<User> spec = Specification.where(null);
    if (inquiry != null && !inquiry.isEmpty()) {
      spec = spec.and((root, query, cb) -> {
        String likePattern = "%" + inquiry.toLowerCase() + "%";
        return cb.or(
            cb.like(cb.lower(root.get("name")), likePattern),
            cb.like(cb.lower(root.get("email")), likePattern),
            cb.like(cb.lower(root.get("website")), likePattern),
            cb.like(cb.lower(root.get("phone")), likePattern),
            cb.like(cb.lower(root.get("username")), likePattern),
            cb.like(cb.lower(root.join("address").get("city")), likePattern),   // ✅ join to address.city
            cb.like(cb.lower(root.join("company").get("name")), likePattern)    // ✅ join to company.name
        );
      });
    }

    Page<User> users = repository.findAll(spec, pageRequest);
    List<UserResponse> userResponses = users.getContent().stream().map(this::mappingToResponse)
        .toList();

    return new PageImpl<>(userResponses, pageRequest, users.getTotalElements());
  }

  private UserResponse mappingToResponse(User user) {
    UserResponse response = new UserResponse();
    BeanUtils.copyProperties(user, response);

    AddressResponse addressResponse = new AddressResponse();
    addressResponse.setStreet(user.getAddress().getStreet());
    addressResponse.setSuite(user.getAddress().getSuite());
    addressResponse.setCity(user.getAddress().getCity());
    addressResponse.setZipcode(user.getAddress().getZipcode());

    GeoResponse geo = new GeoResponse();
    geo.setLat(user.getAddress().getGeo().getLat());
    geo.setLng(user.getAddress().getGeo().getLng());
    addressResponse.setGeo(geo);

    CompanyResponse companyResponse = new CompanyResponse();
    companyResponse.setName(user.getCompany().getName());
    companyResponse.setCatchPhrase(user.getCompany().getCatchPhrase());
    companyResponse.setBs(user.getCompany().getBs());

    response.setAddress(addressResponse);
    response.setCompany(companyResponse);

    return response;
  }

}

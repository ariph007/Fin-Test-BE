package com.alpha.finology.service.impl;

import com.alpha.finology.entity.Department;
import com.alpha.finology.helper.SpecificationHelper;
import com.alpha.finology.model.request.CreateDepartmentRequest;
import com.alpha.finology.model.request.PagingRequest;
import com.alpha.finology.model.request.UpdateDepartmentRequest;
import com.alpha.finology.model.response.DepartmentResponse;
import com.alpha.finology.repository.DepartmentRepository;
import com.alpha.finology.service.DepartmentService;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentRepository repository;

  @Override
  public Page<DepartmentResponse> getAll(PagingRequest pagingRequest, String inquiry,
      String status) {

    PageRequest pageRequest = PageRequest.of(pagingRequest.getPage(), pagingRequest.getPageSize(),
        SpecificationHelper.createSort(pagingRequest.getSortBy()));

    Specification<Department> spec = Specification.where(null);
    if (inquiry != null && !inquiry.isEmpty()) {
      spec = spec.and(SpecificationHelper.inquiryFilter(Arrays.asList("name", "code"), inquiry));
    }

    Page<Department> departments = repository.findAll(spec, pageRequest);
    List<DepartmentResponse> departmentResponses = departments.getContent().stream().map(
        department -> DepartmentResponse.builder().name(department.getName())
            .code(department.getCode()).profitSharing(department.getProfitSharing())
            .id(department.getId()).isActive(department.getIsActive()).build()).toList();

    return new PageImpl<>(departmentResponses, pageRequest, departments.getTotalElements());
  }

  @Override
  public DepartmentResponse getById(String id) {
    Department department = getDepartmentByID(id);
    return DepartmentResponse.builder().id(department.getId()).name(department.getName())
        .code(department.getCode()).profitSharing(department.getProfitSharing()).isActive(department.getIsActive()).build();
  }

  @Override
  public Department add(CreateDepartmentRequest createRequest) {
    boolean isCodeExist = repository.existsByCode(createRequest.getCode());
    if (isCodeExist) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "code already exists.");
    }
    Department department = new Department();
    department.setCode(createRequest.getCode());
    department.setName(createRequest.getName());
    department.setProfitSharing(createRequest.getProfitSharing());
    department.setCreatedBy("admin");
    department.setUpdatedBy("admin");

    ZoneId zoneId = ZoneId.of("UTC");
    ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), zoneId);
    department.setCreatedAt(zonedDateTime);
    department.setUpdatedAt(zonedDateTime);
    department.setIsActive(true);
    return repository.saveAndFlush(department);
  }

  @Override
  public void edit(UpdateDepartmentRequest updateRequest) {
    Department department = getDepartmentByID(updateRequest.getId());
    department.setName(updateRequest.getName());
    department.setProfitSharing(updateRequest.getProfitSharing());
    repository.saveAndFlush(department);
  }

  @Override
  public void delete(String id) {
    boolean isDepartmentExists = repository.existsById(id);
    if (!isDepartmentExists) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "department does not exists.");
    }
    repository.deleteById(id);
  }

  @Override
  public DepartmentResponse getDepartmentById(String id) {
    Department department = getDepartmentByID(id);

    return DepartmentResponse.builder().name(department.getName()).code(department.getCode())
        .profitSharing(department.getProfitSharing()).id(department.getId()).isActive(department.getIsActive()).build();
  }

  @Override
  public void deleteDepartment(String id) {
    Department department = getDepartmentByID(id);
    repository.delete(department);
  }

  @Override
  public void updateDepartment(UpdateDepartmentRequest updateDepartmentRequest) {
    Department department = getDepartmentByID(updateDepartmentRequest.getId());

    if(updateDepartmentRequest.getCode() != null){
      department.setCode(updateDepartmentRequest.getCode());
    }
    if(updateDepartmentRequest.getName() != null){
      department.setName(updateDepartmentRequest.getName());
    }
    if(updateDepartmentRequest.getProfitSharing() != null){
      department.setProfitSharing(updateDepartmentRequest.getProfitSharing());
    }

    if(updateDepartmentRequest.getIsActive() != null){
      department.setIsActive(updateDepartmentRequest.getIsActive());
    }

    repository.saveAndFlush(department);
  }

  @Override
  public Department getDepartmentByID(String id) {
    return repository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "department does not exists.")
    );
  }

}

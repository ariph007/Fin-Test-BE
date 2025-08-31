package com.alpha.finology.service;

import com.alpha.finology.entity.Department;
import com.alpha.finology.model.request.CreateDepartmentRequest;
import com.alpha.finology.model.request.PagingRequest;
import com.alpha.finology.model.request.UpdateDepartmentRequest;
import com.alpha.finology.model.response.DepartmentResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {
  Page<DepartmentResponse> getAll(PagingRequest pagingRequest, String inquiry, String status);

  DepartmentResponse getById(String id);

  Department add(CreateDepartmentRequest createRequest);

  void edit(UpdateDepartmentRequest updateRequest);

  void delete(String id);

  DepartmentResponse getDepartmentById(String id);

  void deleteDepartment(String id);

  void updateDepartment(UpdateDepartmentRequest updateDepartmentRequest);

  Department getDepartmentByID(String id);
}

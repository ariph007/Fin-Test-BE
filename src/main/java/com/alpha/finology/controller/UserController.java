package com.alpha.finology.controller;

import com.alpha.finology.helper.ResponseHelper;
import com.alpha.finology.model.request.PagingRequest;
import com.alpha.finology.model.response.UserResponse;
import com.alpha.finology.model.response.WebResponse;
import com.alpha.finology.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/v1"})
@AllArgsConstructor
@Tag(name = "User")
public class UserController {
  private final UserService service;

  @GetMapping("/users")
  public ResponseEntity<WebResponse<List<UserResponse>>> findAllByPagingAndSearch(
      PagingRequest pagingRequest, String inquiry, String status) {
    return ResponseEntity.ok(ResponseHelper.ok(pagingRequest, service.getAll(pagingRequest, inquiry, status)));
  }
//
//  @PostMapping("/departments")
//  public ResponseEntity<WebResponse<String>> addCategory(
//      @RequestBody CreateDepartmentRequest createDepartmentRequest) {
//    departmentService.add(createDepartmentRequest);
//    return ResponseEntity.ok(ResponseHelper.ok("department has been added successfully."));
//  }
//
//  @GetMapping(
//      value = "/departments/{id}",
//      produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity<WebResponse<DepartmentResponse>> getById(@PathVariable String id) {
//    return ResponseEntity.ok(ResponseHelper.ok(departmentService.getDepartmentById(id)));
//  }
//
//  @DeleteMapping(value = "/departments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//  public ResponseEntity<WebResponse<String>> delete(@PathVariable String id) {
//    departmentService.deleteDepartment(id);
//    return ResponseEntity.ok(ResponseHelper.ok("department has been deleted successfully."));
//  }
//
//  @PutMapping(value = "/departments")
//  public ResponseEntity<WebResponse<String>> updateDepartment(@RequestBody UpdateDepartmentRequest updateDepartmentRequest) {
//    departmentService.updateDepartment(updateDepartmentRequest);
//    return ResponseEntity
//        .ok(ResponseHelper.ok("department has been updated"));
//  }

}

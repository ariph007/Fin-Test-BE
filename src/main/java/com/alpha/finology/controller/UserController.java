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
    return ResponseEntity.ok(ResponseHelper.ok(pagingRequest, service.getAll(pagingRequest, inquiry)));
  }
}

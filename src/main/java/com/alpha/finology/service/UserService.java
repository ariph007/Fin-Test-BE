package com.alpha.finology.service;

import com.alpha.finology.model.request.PagingRequest;
import com.alpha.finology.model.response.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {
  Page<UserResponse> getAll(PagingRequest pagingRequest, String inquiry);



}

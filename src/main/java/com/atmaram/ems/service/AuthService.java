package com.atmaram.ems.service;

import com.atmaram.ems.dto.request.RegisterRequest;
import com.atmaram.ems.dto.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

}
package com.umutgldn.service;

import com.umutgldn.dto.DtoUser;
import com.umutgldn.jwt.AuthRequest;
import com.umutgldn.jwt.AuthResponse;

public interface IAuthService {
	
	public DtoUser register(AuthRequest request);
	
	public AuthResponse authenticate(AuthRequest request);

}

package com.umutgldn.controller;

import com.umutgldn.dto.DtoUser;
import com.umutgldn.jwt.AuthRequest;
import com.umutgldn.jwt.AuthResponse;
import com.umutgldn.jwt.RefreshTokenRequest;

public interface IRestAuthController {
	 
	public DtoUser register(AuthRequest request);
	
	public AuthResponse authenticate(AuthRequest request);
	
	public AuthResponse refreshToken(RefreshTokenRequest request);

}

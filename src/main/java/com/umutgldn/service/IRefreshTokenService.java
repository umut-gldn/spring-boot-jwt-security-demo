package com.umutgldn.service;

import com.umutgldn.jwt.AuthResponse;
import com.umutgldn.jwt.RefreshTokenRequest;

public interface IRefreshTokenService {
	
	public AuthResponse refreshToken(RefreshTokenRequest request);

}

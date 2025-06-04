package com.umutgldn.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umutgldn.jwt.AuthResponse;
import com.umutgldn.jwt.JwtService;
import com.umutgldn.jwt.RefreshTokenRequest;
import com.umutgldn.model.RefreshToken;
import com.umutgldn.model.User;
import com.umutgldn.repository.RefreshTokenRepository;
import com.umutgldn.service.IRefreshTokenService;

@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService{
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private JwtService jwtService;
	
	public boolean isRefreshTokenExpired(Date expireDate) {
		return new Date().before(expireDate);
	}
	

	private RefreshToken creaRefreshToken(User user) {
		RefreshToken refreshToken=new RefreshToken();
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setExpireDate(new Date(System.currentTimeMillis()+1000*60*60*4));
		refreshToken.setUser(user);
		
		return refreshToken;
	}
	
	
	@Override
	public AuthResponse refreshToken(RefreshTokenRequest request) {
		Optional<RefreshToken> optional =refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
		if(optional.isEmpty()) {
			System.out.println("REFRESH TOKEN GEÇERSİZDİR: "+request.getRefreshToken());
		}
		
		RefreshToken refreshToken=optional.get();
		
		if(!isRefreshTokenExpired(refreshToken.getExpireDate()))
		{
			System.out.println("REFRESH TOKEN SÜRESİ DOLMUŞTUR: "+ request.getRefreshToken());	
		}
		
		String accessToken=jwtService.generateToken(refreshToken.getUser());
		RefreshToken savedRefreshToken=refreshTokenRepository.save(creaRefreshToken(refreshToken.getUser()));
		
		return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());
	}

}

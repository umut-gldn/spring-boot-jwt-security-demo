package com.umutgldn.controller;

import com.umutgldn.dto.DtoEmployee;

public interface IRestEmployeeController {
	
	public DtoEmployee findEmployeeById(Long id);

}

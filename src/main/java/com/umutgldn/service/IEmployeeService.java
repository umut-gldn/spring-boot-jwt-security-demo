package com.umutgldn.service;

import com.umutgldn.dto.DtoEmployee;

public interface IEmployeeService {
	
	DtoEmployee findEmployeeById(Long id);

}

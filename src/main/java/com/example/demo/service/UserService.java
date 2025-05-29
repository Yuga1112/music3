package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

public interface UserService {
	
	//회원 등록
	boolean register(UserDTO dto); 
	
	default UserDTO entityToDto(User entity) {
		UserDTO dto = UserDTO.builder()
				.id(entity.getId())
				.name(entity.getName())
				.password(entity.getPassword())
				.role(entity.getRole())
				.build();
		return dto;
				
	}
	
	default User entityToEntity(UserDTO dto) {
		User entity = User.builder()
				.id(dto.getId())
				.name(dto.getName())
				.password(dto.getPassword())
				.role(dto.getRole())
				.build();
		return entity;
}
}
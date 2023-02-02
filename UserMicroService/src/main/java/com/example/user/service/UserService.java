package com.example.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user.entity.UserEntity;
import com.example.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<UserEntity> getAllUsers(){
		return userRepository.findAll();
	}
	
	public String getUserByProductId(int productId){
		return userRepository.findByProductId(productId);
	}
	
	public UserEntity getUserId(int id) {
		return userRepository.findById(id).get();
	}
	
	public void addUser(UserEntity ue){
		userRepository.save(ue);
	}
	
	public void updateUser(int id, UserEntity ue){
		
		if(userRepository.findById(id).isPresent()) {
			
			UserEntity olduserEntity = userRepository.findById(id).get();
			olduserEntity.setUsername(ue.getUsername());
			userRepository.save(olduserEntity);
		}
	}
	
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

}

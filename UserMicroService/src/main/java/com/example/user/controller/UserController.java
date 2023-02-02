package com.example.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.entity.UserEntity;
import com.example.user.service.UserService;

@RestController
@RequestMapping(path="/webapitwo")
public class UserController {
	
	@Autowired
	UserService userService;
	
	//Get all the users
	@GetMapping("/allusers")
	public List<UserEntity> getAllUsers(){
		return userService.getAllUsers();
	}
	
	//This method will give the username by searching with productId
	@GetMapping("/getuserprodid/{productId}")  //"http://localhost:8081/webapitwo/getuserprodid/{productId}"
	public String getUserProdId(@PathVariable int productId) {
		return userService.getUserByProductId(productId);
	}
	
	//Get user by searching with userId
	@GetMapping("/getuser/{userId}")
	public UserEntity getUserId(@PathVariable int userId) {
		return userService.getUserId(userId);
	}
	
	//Add user. The productID given in the body should match with some productId present in the Product table(ProuctMicroService), only then we will be able to combine the data in both the tables.
	@PostMapping("/adduser")
	public void addUser(@RequestBody UserEntity ue) {
		userService.addUser(ue);
	}
	
	//Update user by providing userId
	@PutMapping("/updateuser/{userId}")
	public void updateUser(@PathVariable int userId,@RequestBody UserEntity ue) {
		userService.updateUser(userId, ue);
	}
	
	//Delete user by providing userId
	@DeleteMapping("/deleteuser/{userId}")
	public void deleteUser(@PathVariable int userId) {
		userService.deleteUser(userId);
	}
}

package com.example.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	@Query("SELECT ue.username FROM UserEntity ue WHERE ue.productId=?1")
	public String findByProductId(int productId);
}

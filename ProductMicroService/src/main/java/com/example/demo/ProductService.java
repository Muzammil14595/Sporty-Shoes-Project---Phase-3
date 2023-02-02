package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository; 
	
	RestTemplate restTemplate = new RestTemplate();//Useful for communicating between MicroServices
	
	public List<ProductEntity> getAllProducts(){
		return productRepository.findAll();
	}
	
	public ProductResponse getProd(int productId){
		
		final String uri = "http://localhost:8081/webapitwo/getuserprodid/{productId}";
		
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("productId", productId);
		
		String result = restTemplate.getForObject(uri, String.class, params);//result is going to store username
		
		//Normal code to get data from Product MicroService itself.
		ProductEntity pe = productRepository.findById(productId).get();
		ProductResponse pr = new ProductResponse(); 
		pr.setProductId(pe.getProductId());
		pr.setCategory(pe.getCategory());
		pr.setName(pe.getName());
		pr.setColour(pe.getColour());
		pr.setUser(result);
		
		
		return pr;
	}
	
	public ProductEntity getProduct(int productId) {
		
		return productRepository.findById(productId).get();
	}
	
	
	public void addProduct(ProductEntity pe){
		productRepository.save(pe);
	}
	
	public void updateProduct(int productId, ProductEntity pe){
		
		if(productRepository.findById(productId).isPresent()) {
			
			ProductEntity oldProductEntity = productRepository.findById(productId).get();
			oldProductEntity.setCategory(pe.getCategory());
			oldProductEntity.setName(pe.getName());
			oldProductEntity.setColour(pe.getColour());
			productRepository.save(oldProductEntity);
		}
	}
	
	public void deleteProduct(int productId) {
		productRepository.deleteById(productId);
	}
}

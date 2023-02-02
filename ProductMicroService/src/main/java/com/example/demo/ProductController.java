package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/webapione")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	//Using ProductResponse to get info from UserMicroService
	
	//This method is used to get all the order details by providing the productId. The productID generated in the Product table should match with the productId given in the Users table(UserMicroService) in order to combine the data in both the tables.
	@RequestMapping("/getOrderDetails/{productId}")
	public ProductResponse getOrder(@PathVariable int productId){
		
		return productService.getProd(productId);
	}
	
	
	//Get all products
	@RequestMapping("/allProducts")
	public List<ProductEntity> getAllProducts() {
		
		return productService.getAllProducts();
	}
	
	//Get a product by providing the productId
	@RequestMapping("/getProduct/{productId}")
	public ProductEntity getProductbyID(@PathVariable int productId) {
		
		return productService.getProduct(productId);
	}
	
	//Add a product
	@RequestMapping(method=RequestMethod.POST, value="/addproduct")
    public void addProduct(@RequestBody ProductEntity pe ) {
		
        productService.addProduct(pe);
    }
	
	//Update a product by providing productId
	@RequestMapping(method=RequestMethod.PUT, value="/updateProduct/{productId}")
	public void updateProduct(@PathVariable int productId, @RequestBody ProductEntity pe) {
		
		productService.updateProduct(productId, pe);
	}
	
	//Delete a product
	@RequestMapping(method=RequestMethod.DELETE, value="/deleteProduct/{productId}")
	public void deleteProduct(@PathVariable int productId) {
		
		productService.deleteProduct(productId);
	}
}

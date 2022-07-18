package com.nashtech.assignment.ecommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.nashtech.assignment.ecommerce.DTO.request.ProductRequestDTO;
import com.nashtech.assignment.ecommerce.DTO.request.ProductUpdateDTO;
import com.nashtech.assignment.ecommerce.DTO.respond.ProductRespondDTO;
import com.nashtech.assignment.ecommerce.data.entities.ProductCatogery;

public interface ProductService {
	
	public Page<ProductRespondDTO> getAllProducts(String mode, int page, int size);
	
	public ProductRespondDTO getProductById(int id);
	
	public ResponseEntity<?> deleteProduct(int productId);
	
	
	public ProductRespondDTO updateProduct( ProductUpdateDTO productUpdateDTO);
	
	
	public ProductRespondDTO addNewProduct(ProductRequestDTO productRequest, String catogeryName);
	
	
	public Page<ProductRespondDTO> getListProductByCatogery(int productCatogeryId, String mode, int page, int size);
	
	public List<ProductCatogery> getAllCatogeries();
	
	

	

}

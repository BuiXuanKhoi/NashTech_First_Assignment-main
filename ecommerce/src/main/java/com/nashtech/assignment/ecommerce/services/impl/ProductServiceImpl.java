package com.nashtech.assignment.ecommerce.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.nashtech.assignment.ecommerce.data.entities.Users;
import com.nashtech.assignment.ecommerce.data.repository.UserRepository;
import com.nashtech.assignment.ecommerce.exception.ApiDeniedException;
import com.nashtech.assignment.ecommerce.security.localuser.UserLocal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nashtech.assignment.ecommerce.DTO.request.ProductRequestDTO;
import com.nashtech.assignment.ecommerce.DTO.request.ProductUpdateDTO;
import com.nashtech.assignment.ecommerce.DTO.respond.ProductRespondDTO;
import com.nashtech.assignment.ecommerce.data.entities.ProductCatogery;
import com.nashtech.assignment.ecommerce.data.entities.Products;
import com.nashtech.assignment.ecommerce.data.repository.ProductCatogeryRepository;
import com.nashtech.assignment.ecommerce.data.repository.ProductRepository;
import com.nashtech.assignment.ecommerce.exception.ResourceNotFoundException;
//import com.nashtech.assignment.ecommerce.security.jwt.JwtAuthEntryPoint;
import com.nashtech.assignment.ecommerce.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
		
	private final ModelMapper modelMapper;
	
	private final ProductCatogeryRepository productCatogeryRepository;

	private final UserLocal userLocal;

	private final UserRepository userRepository;
	


	
	
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, ProductCatogeryRepository productCatogeryRepository,
							  UserLocal userLocal, UserRepository userRepository) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
		this.productCatogeryRepository = productCatogeryRepository;
		this.userLocal = userLocal;
		this.userRepository = userRepository;
	}
	

	@Override
	public Page<ProductRespondDTO> getAllProducts(String mode, int page, int size)
	{

		Pageable pageable = createPage(mode, page, size);
		Page<Products> pageProducts = this.productRepository.getPageProducts(pageable);
		if(pageProducts.hasContent()) 
		{
			Page<ProductRespondDTO> pageProductDTO = pageProducts.map(this::convertToProductDTO);
			return pageProductDTO;
		}
		
		throw new ResourceNotFoundException("Cannot get Page at this size");
	}
	
	
	
	@Override
	public Page<ProductRespondDTO> getListProductByCatogery(int productCatogeryId, String mode, int page, int size)
	{		
		Pageable pageable = createPage(mode,page,size);
		Page<Products> pageProducts = this.productRepository.getPageProductByCatogery(productCatogeryId, pageable);

		if( pageProducts != null) 
		{
			Page<ProductRespondDTO> pageProductDTO = pageProducts.map(this::convertToProductDTO);
			return pageProductDTO;
		} 
		
		throw new ResourceNotFoundException("Catogery Not Found !");
	}
	
	

	@Override
	public ProductRespondDTO getProductById(int id)
	{
		Optional<Products> productOptional = this.productRepository.findById(id);
		
		if(productOptional.isPresent()) 
		{
			Products products = productOptional.get();
			return modelMapper.map(products, ProductRespondDTO.class);
		}
		
		throw new ResourceNotFoundException("Product Not Found With ID "+ id);
	}



	@Override
	public ProductRespondDTO updateProduct(ProductUpdateDTO productUpdateDTO)
	{
		String userName = userLocal.getLocalUser();
		Users users = this.userRepository.findByUserName(userName).get();
		Optional<Products> productOptinal = productRepository.findById(productUpdateDTO.getProductId());
			if(productOptinal.isPresent())
			{
				Products utilProducts = productOptinal.get();
				if (users == utilProducts.getUsers())
				{
					ProductCatogery productCatogery = utilProducts.getProductCatogery();

					Products products = modelMapper.map(productUpdateDTO, Products.class);
					products.setProductCatogery(productCatogery);
					products.setProductUpdateDay(new Date());

					Products savedProduct = this.productRepository.save(products);
					return modelMapper.map(savedProduct, ProductRespondDTO.class);
				}
				throw new ApiDeniedException("Only Product's Owner can update this product");
			}
			throw new ResourceNotFoundException("Product Not Found With ID : " + productUpdateDTO.getProductId());
	}


	@Override
	public ProductRespondDTO addNewProduct(ProductRequestDTO productRequest, String catogeryName)
	{
		Products products = modelMapper.map(productRequest, Products.class);
		String userName = userLocal.getLocalUser();

		Users users = userRepository.findByUserName(userName).get();
		
		ProductCatogery productCatogery = productCatogeryRepository.getCatogeryByName(catogeryName);
		products.setProductCatogery(productCatogery);
		products.setProductCreateDay(new Date());
		products.setUsers(users);
		
		Products savedProducts = this.productRepository.save(products);				
		ProductRespondDTO productRespondDTO =  modelMapper.map(savedProducts, ProductRespondDTO.class);
		productRespondDTO.setUserName(users.getUserName());
		return  productRespondDTO;
	}



	
	private ProductRespondDTO convertToProductDTO(Products products) {
		ProductRespondDTO productRespondDTO= modelMapper.map(products, ProductRespondDTO.class);
		productRespondDTO.setUserName(products.getUsers().getUserName());
		return  productRespondDTO;
	}
	
	
	public Pageable createPage(String mode, int page, int size) 
	{
		Sort sort = Sort.by("product_price").ascending();
		
		if(mode.equals("d")) 
		{
			sort = Sort.by("product_price").descending();
		}
		
		return PageRequest.of(page, size, sort);
		
	}


	@Override
	public ResponseEntity<?> deleteProduct(int productId)
	{
		String userName = userLocal.getLocalUser();
		Users users = this.userRepository.findByUserName(userName).get();
		Optional<Products> productsOptional = this.productRepository.findById(productId);
		if(productsOptional.isPresent())
		{
			Products products = productsOptional.get();
			if(products.getUsers().equals(users))
			{
				this.productRepository.delete(products);
				return ResponseEntity.ok().body("Delete Successs");
			}
			throw new ApiDeniedException("Only Product Owner can delete product");
		}
		throw new ResourceNotFoundException("Product not found with ID : " + productId);
	}


	@Override
	public List<ProductCatogery> getAllCatogeries() {
		return this.productCatogeryRepository.findAll();
	}





	


	
	

}

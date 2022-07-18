package com.nashtech.assignment.ecommerce.controllers.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.nashtech.assignment.ecommerce.DTO.request.CartDetailRequestDTO;
import com.nashtech.assignment.ecommerce.DTO.respond.MessageRespond;
import com.nashtech.assignment.ecommerce.data.entities.CartItems;
import com.nashtech.assignment.ecommerce.service.CarItemService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/carts")
public class CartController {
	
	@Autowired
	private CarItemService carItemService;
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('ADMIN')")
	public List<CartItems> getAllCartByOwner(){
		return this.carItemService.getAllCartItemsByOwner();	
	}
	
	@PutMapping
	@PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('ADMIN')")
	public CartItems saveCartItems(
			@Valid @RequestBody CartDetailRequestDTO cartDetailRequestDTO,
			@RequestParam(name = "product", required = true) int productId
			) {
		return this.carItemService.saveCartItem(cartDetailRequestDTO, productId);
	}
	
	@DeleteMapping
	@PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('ADMIN')")
	public ResponseEntity<?> deleteAllCartItemByOwner(){
		this.carItemService.deleteAllCartItemByOwner();
		return ResponseEntity.ok(new MessageRespond(HttpStatus.OK.value(),"Delete Success")); 
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('ADMIN')")
	public ResponseEntity<?> deleteCartItem(
			@PathVariable("id") int cartItemId){
		return this.carItemService.deleteCartItem(cartItemId);
	}
	
	

}

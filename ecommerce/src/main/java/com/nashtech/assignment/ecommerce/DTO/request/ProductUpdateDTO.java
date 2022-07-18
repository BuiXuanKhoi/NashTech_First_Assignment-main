package com.nashtech.assignment.ecommerce.DTO.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductUpdateDTO {
	
	@NotNull(message = "Id is required")
	private int productId;

	@JsonProperty("name")
	@NotNull(message = "name is required")
	private String productName;

	@JsonProperty("price")
	@NotNull(message = "price is required")
	private int productPrice;

	@JsonProperty("describe")
	private String productDescribe;

	@JsonProperty("image")
	private String productImage;

	@NotNull(message = "quantity is required")
	@JsonProperty("quantity")
	private int productQuantity;
	

}

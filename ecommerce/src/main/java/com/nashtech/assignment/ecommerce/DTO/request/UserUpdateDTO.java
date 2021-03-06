package com.nashtech.assignment.ecommerce.DTO.request;

import java.sql.Date;

import javax.validation.constraints.Min;
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
public class UserUpdateDTO {

	@JsonProperty("id")
	@NotNull(message = "Id cannot be null")
	private int userId;
	
	@NotBlank(message = "email cannot blank")
	@JsonProperty("email")
	private String userEmail;
	
	@Min(value = 8, message = "Password at least has 8 characters")
	@JsonProperty("password")
	private String userPassword;
	
	@JsonProperty("name")
	private String userName;


	private String role;




}

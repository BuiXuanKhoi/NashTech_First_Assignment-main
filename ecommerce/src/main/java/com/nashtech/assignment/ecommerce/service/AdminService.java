package com.nashtech.assignment.ecommerce.service;

import com.nashtech.assignment.ecommerce.DTO.respond.ProductRespondDTO;
import com.nashtech.assignment.ecommerce.data.entities.ProductCatogery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    public ResponseEntity<?> blockUser();

    public ResponseEntity<?> sendUnbanEmail();

    public ResponseEntity<?> updateUser();

    public ResponseEntity<?> deleteProducts();

    public ProductCatogery createNewCatogery();

    public ProductRespondDTO updateProducts();





}

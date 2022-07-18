package com.nashtech.assignment.ecommerce.services.impl;

import com.nashtech.assignment.ecommerce.DTO.respond.ProductRespondDTO;
import com.nashtech.assignment.ecommerce.data.entities.ProductCatogery;
import com.nashtech.assignment.ecommerce.data.entities.Users;
import com.nashtech.assignment.ecommerce.data.repository.AdminRepository;
import com.nashtech.assignment.ecommerce.data.repository.ProductRepository;
import com.nashtech.assignment.ecommerce.data.repository.UserRepository;
import com.nashtech.assignment.ecommerce.service.AdminService;
import com.nashtech.assignment.ecommerce.service.ProductService;
import com.nashtech.assignment.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class AdminServiceImpl implements AdminService {

    private UserRepository userRepository;
    private AdminRepository adminRepository;
    private ProductRepository productRepository;
    private UserService userService;
    private ProductService productService;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, AdminRepository adminRepository, ProductRepository productRepository,
                            UserService userService, ProductService productService) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public ResponseEntity<?> blockUser() {
        return null;
    }

    @Override
    public ResponseEntity<?> sendUnbanEmail() {
        return null;
    }

    @Override
    public ResponseEntity<?> updateUser() {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteProducts() {
        return null;
    }

    @Override
    public ProductCatogery createNewCatogery() {
        return null;
    }

    @Override
    public ProductRespondDTO updateProducts() {
        return null;
    }
}

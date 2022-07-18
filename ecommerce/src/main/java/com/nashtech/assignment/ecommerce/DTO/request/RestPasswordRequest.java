package com.nashtech.assignment.ecommerce.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RestPasswordRequest {


    @JsonProperty("id")
    private int userId;

    @JsonProperty("password")
    private String userPassword;
}

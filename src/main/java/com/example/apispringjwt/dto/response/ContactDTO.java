package com.example.apispringjwt.dto.response;

public record ContactDTO (
        Integer id,
        String contactEmail,
        String telephone,
        String contactName,
        String image
){
}

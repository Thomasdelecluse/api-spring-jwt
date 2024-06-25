package com.example.apispringjwt.dto.response;

import com.example.apispringjwt.model.User;

public record ContactDTO (
        Integer id,
        String contactEmail,
        String telephone,
        String contactName
){
}

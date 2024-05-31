package com.example.apispringjwt.dto.request;

public record CreateMessageDTO(
         String author,
         String destination,
         String message
) {
}

package br.com.ju.model;

public record CreateUserResponse(
        String id,
        String name,
        String job,
        String createdAt) {}
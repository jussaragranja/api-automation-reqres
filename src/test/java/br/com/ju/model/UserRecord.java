package br.com.ju.model;

public record UserRecord(
        int id,
        String email,
        String first_name,
        String last_name,
        String avatar) {}
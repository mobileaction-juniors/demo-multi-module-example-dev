package co.mobileaction.example.common.dto;

import lombok.Builder;

@Builder
public record UserDto(Long id, String name, String username, String email) {}

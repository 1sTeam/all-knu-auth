package com.example.allknuauth.auth.adapter.in.web;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KnuLoginResource {
    @NotNull(message = "아이디가 비었다")
    private String id;
    @NotNull(message = "비밀번호가 비었다")
    private String password;
}

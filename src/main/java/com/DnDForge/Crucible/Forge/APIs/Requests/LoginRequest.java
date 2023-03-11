package com.DnDForge.Crucible.Forge.APIs.Requests;

import lombok.Data;

@Data
public class LoginRequest {
    String email;
    String password;
}

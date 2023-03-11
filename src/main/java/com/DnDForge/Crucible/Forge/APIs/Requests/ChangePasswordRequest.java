package com.DnDForge.Crucible.Forge.APIs.Requests;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    String email;
    String password;
}

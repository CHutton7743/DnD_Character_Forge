package com.DnDForge.Crucible.Forge.APIs.Requests;

import lombok.Data;

@Data
public class ChangeUserNameRequest {
    String username;
    String email;
}

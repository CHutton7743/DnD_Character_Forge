package com.DnDForge.Crucible.Forge.APIs;

import com.DnDForge.Crucible.Forge.APIs.APIComponents.UserService;
import com.DnDForge.Crucible.Forge.APIs.Requests.ChangeEmailRequest;
import com.DnDForge.Crucible.Forge.APIs.Requests.ChangePasswordRequest;
import com.DnDForge.Crucible.Forge.APIs.Requests.ChangeUserNameRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/user")
public class UserAPI {
    @Autowired
    UserService userService;

    @PostMapping("/updateEmail")
    public String changeEmail(@RequestBody ChangeEmailRequest request) {
        userService.changeEmail(request);
        return "Email changed successfully";
    }

    @PostMapping("/updatePassword")
    public String changePassword(@RequestBody ChangePasswordRequest request) {
       userService.changePassword(request);
       return "Password changed successfully";
    }

    @PostMapping("/updateUsername")
    public String changeUsername(@RequestBody ChangeUserNameRequest request) {
        userService.changeUserName(request);
        return "Username changed successfully";
    }
}

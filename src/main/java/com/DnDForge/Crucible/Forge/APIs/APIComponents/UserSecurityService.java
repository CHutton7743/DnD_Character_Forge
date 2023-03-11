package com.DnDForge.Crucible.Forge.APIs.APIComponents;

import com.DnDForge.Crucible.Forge.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService {
    @Autowired
    private UserRepository userRepository;

}

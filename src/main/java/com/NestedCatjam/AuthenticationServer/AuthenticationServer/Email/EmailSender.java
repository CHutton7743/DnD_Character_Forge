package com.NestedCatjam.AuthenticationServer.AuthenticationServer.Email;

public interface EmailSender {
    void send(String to, String email);
}

package com.testautomation.pojos;

public class User {
    private String userId;
    private String username;
    private String email;
    private String password;

    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    public User setUserId(String userId) { this.userId = userId; return this; }
    public User setUsername(String username) { this.username = username; return this; }
    public User setEmail(String email) { this.email = email; return this; }
    public User setPassword(String password) { this.password = password; return this; }
}

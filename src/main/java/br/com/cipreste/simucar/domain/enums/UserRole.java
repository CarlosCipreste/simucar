package br.com.cipreste.simucar.domain.enums;

public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String role;

    private UserRole(String role) {
        this.role = role;
    }

    private String getRole(){
        return role;
    }
}

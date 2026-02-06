package com.example.enterpriserag.security;

public record RoleMapping(String principal, String role) {

    public static RoleMapping fromApiKey(String apiKey) {
        return switch (apiKey) {
            case "admin-key" -> new RoleMapping("platform-admin", "ADMIN");
            case "analyst-key" -> new RoleMapping("search-analyst", "ANALYST");
            case "auditor-key" -> new RoleMapping("compliance-auditor", "AUDITOR");
            default -> null;
        };
    }
}

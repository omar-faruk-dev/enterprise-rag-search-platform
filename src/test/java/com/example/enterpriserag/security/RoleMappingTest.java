package com.example.enterpriserag.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RoleMappingTest {

    @Test
    void shouldResolveAnalystRole() {
        RoleMapping roleMapping = RoleMapping.fromApiKey("analyst-key");
        assertThat(roleMapping.role()).isEqualTo("ANALYST");
    }

    @Test
    void shouldReturnNullForUnknownKey() {
        assertThat(RoleMapping.fromApiKey("bad-key")).isNull();
    }
}

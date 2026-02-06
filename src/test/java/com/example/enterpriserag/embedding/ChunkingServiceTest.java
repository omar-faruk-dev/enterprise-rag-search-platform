package com.example.enterpriserag.embedding;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class ChunkingServiceTest {

    private final ChunkingService service = new ChunkingService();

    @Test
    void shouldChunkWithOverlap() {
        List<String> chunks = service.split("abcdefghij", 4, 1);
        assertThat(chunks).containsExactly("abcd", "defg", "ghij", "j");
    }
}

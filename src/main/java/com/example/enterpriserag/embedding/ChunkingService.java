package com.example.enterpriserag.embedding;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ChunkingService {

    public List<String> split(String content, int chunkSize, int overlap) {
        String sanitized = content == null ? "" : content.trim();
        if (sanitized.isEmpty()) {
            return List.of();
        }

        List<String> chunks = new ArrayList<>();
        int step = Math.max(1, chunkSize - overlap);
        for (int start = 0; start < sanitized.length(); start += step) {
            int end = Math.min(sanitized.length(), start + chunkSize);
            chunks.add(sanitized.substring(start, end));
            if (end == sanitized.length()) {
                break;
            }
        }
        return chunks;
    }
}

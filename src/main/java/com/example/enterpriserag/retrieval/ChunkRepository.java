package com.example.enterpriserag.retrieval;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChunkRepository extends JpaRepository<ChunkRecord, Long> {
    List<ChunkRecord> findTop5ByTenantIdAndChunkTextContainingIgnoreCase(String tenantId, String query);
}

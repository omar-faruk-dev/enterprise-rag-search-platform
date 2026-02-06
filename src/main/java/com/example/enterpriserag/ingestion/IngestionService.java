package com.example.enterpriserag.ingestion;

import com.example.enterpriserag.embedding.ChunkingService;
import com.example.enterpriserag.embedding.EmbeddingService;
import com.example.enterpriserag.retrieval.ChunkRecord;
import com.example.enterpriserag.retrieval.ChunkRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngestionService {

    private final DocumentRepository documentRepository;
    private final ChunkingService chunkingService;
    private final EmbeddingService embeddingService;
    private final ChunkRepository chunkRepository;

    public IngestionService(DocumentRepository documentRepository,
                            ChunkingService chunkingService,
                            EmbeddingService embeddingService,
                            ChunkRepository chunkRepository) {
        this.documentRepository = documentRepository;
        this.chunkingService = chunkingService;
        this.embeddingService = embeddingService;
        this.chunkRepository = chunkRepository;
    }

    @Transactional
    public Long ingest(IngestionRequest request) {
        DocumentRecord record = new DocumentRecord();
        record.setTenantId(request.tenantId());
        record.setSource(request.source());
        record.setClassification(request.classification());
        record.setContent(request.content());

        DocumentRecord saved = documentRepository.save(record);
        List<String> chunks = chunkingService.split(request.content(), 450, 50);

        for (String chunk : chunks) {
            ChunkRecord chunkRecord = new ChunkRecord();
            chunkRecord.setDocumentId(saved.getId());
            chunkRecord.setTenantId(request.tenantId());
            chunkRecord.setChunkText(chunk);
            chunkRecord.setEmbeddingRef(embeddingService.embedAndStore(request.tenantId(), chunk));
            chunkRepository.save(chunkRecord);
        }

        return saved.getId();
    }
}

# enterprise-rag-search-platform

A production-oriented Spring Boot + Spring AI Retrieval-Augmented Generation backend designed for regulated SaaS and gov-tech environments.

## System Diagram (Description)

The platform is organized as a layered service:

1. **API Layer (`/api`)** receives authenticated requests for ingestion, query, and evaluation.
2. **Security Layer (`/security`)** enforces API-key based RBAC roles (`ADMIN`, `ANALYST`, `AUDITOR`).
3. **Ingestion Layer (`/ingestion`)** stores source documents and triggers chunk generation.
4. **Embedding Layer (`/embedding`)** transforms chunks into vectors and writes references for pgvector-backed storage.
5. **Retrieval Layer (`/retrieval`)** fetches tenant-scoped chunks to build context windows.
6. **Model Abstraction Layer (`/llm`)** decouples orchestration from underlying providers (Spring AI/OpenAI default).
7. **Audit Layer (`/audit`)** records API activity into durable audit tables.
8. **Evaluation Layer (`/evaluation`)** runs lightweight relevance checks for operational quality.

## RAG Flow

1. Client sends a document ingestion request with tenant and classification metadata.
2. The ingestion service persists the raw document.
3. The chunking service segments content into overlap-aware windows.
4. Embedding service computes vectors and stores pgvector references.
5. Query endpoint retrieves the top matching chunks by tenant.
6. Prompt guardrails sanitize user input and apply a constrained system prompt.
7. LLM client returns grounded answer + chunk citations.
8. Audit trail captures who executed the action and against which endpoint.

## Repository Structure

- `src/main/java/com/example/enterpriserag/ingestion` - document ingest pipeline
- `src/main/java/com/example/enterpriserag/embedding` - chunking + embedding abstraction
- `src/main/java/com/example/enterpriserag/retrieval` - retrieval and orchestration
- `src/main/java/com/example/enterpriserag/api` - REST controllers
- `src/main/java/com/example/enterpriserag/security` - API auth + RBAC
- `src/main/java/com/example/enterpriserag/evaluation` - evaluation harness
- `src/main/resources/db/migration` - Flyway migrations
- `ingestion`, `embedding`, `retrieval`, `api`, `security`, `evaluation` - domain docs
- `docs` - compliance notes and usage examples

## Deployment

### Local runtime
```bash
docker compose up --build
```

### API base URL
`http://localhost:8080/api/v1`

### Environment variables
- `OPENAI_API_KEY` - provider key for Spring AI OpenAI starter.
- `SPRING_DATASOURCE_*` - datasource overrides for production clusters.

## Security Posture

- **Authentication:** API key header (`X-API-KEY`) with explicit role mapping.
- **Authorization:** method-level `@PreAuthorize` controls by role.
- **Auditing:** every `/api/**` call writes actor + method + path to `audit_events`.
- **Prompt safety:** prompt sanitization and restrictive base system prompt.
- **Data isolation:** all retrieval operations include `tenantId` filter.

## CI/CD

GitHub Actions workflow runs Java 21 tests on push/PR (`.github/workflows/ci.yml`).

## Sample tests

- retrieval orchestration with mocked LLM client
- evaluation scoring harness
- role mapping checks
- chunking strategy checks

Run tests:
```bash
mvn test
```

## Future Enterprise Extensions

- OIDC/JWT integration with external IAM (Okta, Entra ID, Keycloak)
- policy-as-code authorization (OPA)
- encryption-at-rest keys managed by KMS/HSM
- signed prompt templates and versioned model registry
- offline benchmark suite for hallucination and safety regression

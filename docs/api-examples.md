# API Examples

## Ingest document
```bash
curl -X POST http://localhost:8080/api/v1/ingestion \
  -H 'Content-Type: application/json' \
  -H 'X-API-KEY: analyst-key' \
  -d '{"tenantId":"gov-west","source":"policy.pdf","classification":"CUI","content":"Retention period is 7 years."}'
```

## Query RAG
```bash
curl -X POST http://localhost:8080/api/v1/rag/query \
  -H 'Content-Type: application/json' \
  -H 'X-API-KEY: auditor-key' \
  -d '{"tenantId":"gov-west","query":"What is the retention period?"}'
```

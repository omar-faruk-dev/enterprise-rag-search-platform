# Compliance Notes

## Regulatory alignment signals
- FISMA/NIST 800-53: explicit audit logging and role-based access controls.
- SOC2 CC6/CC7: immutable audit events and least-privilege API roles.
- Data governance: tenantId scoping through ingestion and retrieval pathways.

## Guardrails
- Prompt sanitization strips common prompt injection and destructive SQL terms.
- System prompts constrain generated output to retrieved context.

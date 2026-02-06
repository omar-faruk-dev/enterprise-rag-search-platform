package com.example.enterpriserag.audit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "audit_events")
public class AuditEventRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String actor;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private OffsetDateTime at = OffsetDateTime.now();

    public void setActor(String actor) { this.actor = actor; }
    public void setAction(String action) { this.action = action; }
    public void setPath(String path) { this.path = path; }
}

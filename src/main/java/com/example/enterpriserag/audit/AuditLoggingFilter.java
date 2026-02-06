package com.example.enterpriserag.audit;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuditLoggingFilter extends OncePerRequestFilter {

    private final AuditEventRepository auditEventRepository;

    public AuditLoggingFilter(AuditEventRepository auditEventRepository) {
        this.auditEventRepository = auditEventRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        filterChain.doFilter(request, response);

        if (request.getRequestURI().startsWith("/api/")) {
            AuditEventRecord event = new AuditEventRecord();
            String actor = SecurityContextHolder.getContext().getAuthentication() != null
                    ? SecurityContextHolder.getContext().getAuthentication().getName()
                    : "anonymous";
            event.setActor(actor);
            event.setAction(request.getMethod());
            event.setPath(request.getRequestURI());
            auditEventRepository.save(event);
        }
    }
}

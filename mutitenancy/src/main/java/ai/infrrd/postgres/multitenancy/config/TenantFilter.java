package ai.infrrd.postgres.multitenancy.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TenantFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String tenant = request.getHeader("X-TenantID");
        if (tenant == null || tenant.isBlank()) {
            tenant = "public"; // default schema
        }
        try {
            TenantContext.setCurrentTenant(tenant);
            chain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }
}


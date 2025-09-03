package odata.demo.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class ODataAccessLogFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(ODataAccessLogFilter.class);
    private static final AntPathMatcher pm = new AntPathMatcher();

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Логируем только OData; х2-консоль и статику — мимо
        String p = request.getRequestURI();
        return !pm.match("/odata.svc/**", p);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        var reqW = new ContentCachingRequestWrapper(request);
        var respW = new ContentCachingResponseWrapper(response);

        long start = System.nanoTime();
        int status = 500;
        try {
            filterChain.doFilter(reqW, respW);
            status = respW.getStatus();
        } finally {
            long tookMs = (System.nanoTime() - start) / 1_000_000;

            String method = request.getMethod();
            String uri = request.getRequestURI();
            String qs = request.getQueryString();
            if (qs != null && !qs.isEmpty()) {
                uri += "?" + URLDecoder.decode(qs, StandardCharsets.UTF_8);
            }

            // кто
            String user = "-";
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.isAuthenticated()) {
                user = auth.getName();
            }

            // размеры
            int reqBytes = reqW.getContentAsByteArray() != null ? reqW.getContentAsByteArray().length : 0;
            // если клиент прислал Content-Length — можно взять его
            if (reqBytes == 0) {
                int headerLen = request.getContentLength();
                if (headerLen > 0) reqBytes = headerLen;
            }
            int respBytes = respW.getContentAsByteArray() != null ? respW.getContentAsByteArray().length : 0;

            log.info("OData {} {} -> {} {}ms user={} req={}B resp={}B",
                    method, uri, status, tookMs, user, reqBytes, respBytes);

            // важно: вернуть тело клиенту
            respW.copyBodyToResponse();
        }
    }
}


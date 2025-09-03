package odata.demo.component;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@Configuration
public class ODataDocsServletConfig {

    @Bean
    ServletRegistrationBean<HttpServlet> odataDocsServlet() {
        HttpServlet servlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                // серверный форвард в статический index.html
                req.getRequestDispatcher("/index.html").forward(req, resp);
            }
        };
        // ВАЖНО: точное сопоставление — "/odata.svc/index.html"
        var reg = new ServletRegistrationBean<>(servlet, "/odata.svc/index.html");
        reg.setName("ODataDocsServlet");
        reg.setLoadOnStartup(2);
        return reg;
    }

    // odata/demo/component/ODataDocsServletConfig.java
    @Bean
    ServletRegistrationBean<HttpServlet> odataCollectionServlet() {
        HttpServlet servlet = new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException {
                req.getRequestDispatcher("/collection.json").forward(req, resp);
            }
        };
        var reg = new ServletRegistrationBean<>(servlet, "/odata.svc/collection.json");
        reg.setName("ODataDocsCollectionServlet");
        reg.setLoadOnStartup(2);
        return reg;
    }

}

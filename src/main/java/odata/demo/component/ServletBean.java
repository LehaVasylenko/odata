package odata.demo.component;

import org.apache.olingo.odata2.core.servlet.ODataServlet;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletBean {

    @Bean
    ServletRegistrationBean<ODataServlet> odataServlet() {
        var servlet = new ODataServlet();
        var reg = new ServletRegistrationBean<>(servlet, "/odata.svc/*");
        reg.setName("ODataServlet");
        reg.setLoadOnStartup(1);
        reg.addInitParameter(
                ODataServiceFactory.FACTORY_LABEL,
                "odata.demo.component.ODataJPAServiceFactory"
        );
        return reg;
    }
}

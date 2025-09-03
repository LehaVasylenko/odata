// package odata.demo.logging;
package odata.demo.logging;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ODataAccessLogConfig {

    @Bean
    public FilterRegistrationBean<ODataAccessLogFilter> odataAccessLogFilter() {
        var reg = new FilterRegistrationBean<>(new ODataAccessLogFilter());
        reg.setName("odataAccessLogFilter");
        reg.addUrlPatterns("/odata.svc/*");
        // порядок можно не трогать — фильтры вызываются до сервлета,
        // security остаётся раньше по цепочке.
        return reg;
    }
}

package odata.demo.component;

import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;

public class ODataJPAServiceFactory extends org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory {

    @Override
    public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
        var ctx = getODataJPAContext();
        var req = (HttpServletRequest) ctx.getODataContext()
                .getParameter(ODataContext.HTTP_SERVLET_REQUEST_OBJECT);

        var wac = WebApplicationContextUtils.getRequiredWebApplicationContext(req.getServletContext());
        EntityManagerFactory emf = wac.getBean(EntityManagerFactory.class);

        ctx.setEntityManagerFactory(emf);
        ctx.setPersistenceUnitName("default");
        // НЕ вызывай setContainerManaged(true) и НЕ подсовывай готовый EM — Olingo сам управляет
        return ctx;
    }

}

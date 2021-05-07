package ayi.microservicios.zuulserver.filters;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PreElapsedTimeFilter extends ZuulFilter{

    private static Logger log = LoggerFactory.getLogger(PreElapsedTimeFilter.class);

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //se necesita pasar datos al request por lo tanto obtenemos el contexto
        RequestContext context = RequestContext.getCurrentContext(); 
        //con el contexto podemos ya obtener la request
        HttpServletRequest request = context.getRequest();
        
        //para poder chequear la request
        log.info(String.format("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString()));
        
        //ya podemos pasarle datos al request
        //obtenemos el tiempo inicial
        Long initialTime = System.currentTimeMillis();
        //seteamos el atributo en la request
        request.setAttribute("initialTime", initialTime);
        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }
    
}

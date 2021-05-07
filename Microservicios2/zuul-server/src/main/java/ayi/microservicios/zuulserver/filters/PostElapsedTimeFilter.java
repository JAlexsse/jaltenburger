package ayi.microservicios.zuulserver.filters;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class PostElapsedTimeFilter extends ZuulFilter{

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
        log.info("Entrando a post filter.");
        
        //ya podemos pasarle datos al request
        //como ya le pasamos a la pre request el tiempo inicial como atributos lo recuperamos y casteamos para operar
        Long initialTime = (Long)request.getAttribute("initialTime");
        Long finalTime = System.currentTimeMillis();

        Long elapsedTime = finalTime - initialTime;
        //mostramos resultado en el log
        log.info(String.format("Tiempo transcurrido en segundos %s", elapsedTime.doubleValue()/1000.00));
        
        return null;
    }

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 2;
    }
    
}

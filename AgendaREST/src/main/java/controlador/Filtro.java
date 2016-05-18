package controlador;

import javax.ws.rs.container.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by oscar on 10/05/16.
 */
@Anotacion
@Provider
public class Filtro implements ContainerRequestFilter, ContainerResponseFilter{
    @Context
    ResourceInfo info;

    public Filtro() {
        System.out.println("Tenemos un filtro");
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
//        System.out.println("Hola" + info.toString());
        containerRequestContext.setProperty("coso", "Coso");
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.setStatus(201);
    }
}

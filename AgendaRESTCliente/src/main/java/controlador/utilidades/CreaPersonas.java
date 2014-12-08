package controlador.utilidades;

import es.uji.www.GeneradorDatosINE;
import modelo.datos.Persona;
import modelo.persistencia.BaseDatos;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by oscar on 30/11/14.
 */
@Path("crea")
@Stateless
public class CreaPersonas {
    private GeneradorDatosINE generador = new GeneradorDatosINE();
    @Inject
    private BaseDatos  bd;

    @POST
    @Path("{cuantas}")
    public Response creaPersonas(@PathParam("cuantas") int cuantas) {
        Persona[] personas = new Persona[cuantas];
        Persona persona;
        for(int i = 0; i < cuantas; i++) {
            persona = new Persona();
            persona.setNombre(generador.getNombre());
            persona.setApellidos(generador.getApellido());
            persona.setNif(generador.getNIF());
            bd.nuevaPersona(persona);
            personas[i] = persona;
        }
        Response response = Response.ok(personas).build();
        return response;
    }
}

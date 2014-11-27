package servicios;

import modelo.dao.PersonaDAO;
import modelo.datos.Persona;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by oscar on 27/11/14.
 */
@Path("personas")
public class PersonaServicios {
    @Inject
    PersonaDAO personaDAO;

    public PersonaServicios() {
        super();
    }

    @GET
    @Path("{nif}")
    @Produces("application/json")
    public Response buscarPersonaPorNIF(@PathParam("nif") String nif) {
        Persona persona = personaDAO.buscaPersonaPorNIF(nif);
        if (persona == personaDAO.ENTRADA_NULL)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(persona).build();
    }

    @GET
    @Produces("application/json")
    public Response listaTodasPersonas() {
        Persona[] personas = personaDAO.listaTodasPersonas();
        return Response.ok(personas).build();
    }

//    @POST
    @GET
    @Produces("application/json")
    @Path("crea/{cuantas}")
    public Response anyadePersonas(@PathParam("cuantas") int cuantas) {
        return Response.ok(personaDAO.anyadePersonas(cuantas)).build();
    }
}

package controlador;

import modelo.dao.PersonaDAO;
import modelo.datos.Persona;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.*;
import java.net.URI;

/**
 * Created by oscar on 27/11/14.
 */
@Path("personas")
//@Anotacion
public class PersonaServicios {
    @Inject
    PersonaDAO personaDAO;
    @Context
    private UriInfo uriInfo;


    public PersonaServicios() {
        super();
    }

    @GET
    @Path("{nif}")
    @Produces("application/xml")
    public Response buscarPersonaPorNIF(@PathParam("nif") String nif) {
        Persona persona = personaDAO.buscaPersonaPorNIF(nif);
        if (persona == personaDAO.ENTRADA_NULL)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(persona).build();
    }

    @GET
    @Produces("application/json")
    @Anotacion
    public Response listaTodasPersonas() {
        Persona[] personas = personaDAO.listaTodasPersonas();
        return Response.ok(personas).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response nuevaEntradaDesdeFormulario(
            @FormParam("nombre") String nombre,
            @FormParam("apellidos") @DefaultValue("") String apellidos,
            @FormParam("nif") String nif) {
        if (personaDAO.buscaPersonaPorNIF(nif) == PersonaDAO.ENTRADA_NULL) {
            Persona persona = new Persona(nombre, apellidos, nif);
            personaDAO.nuevaPersona(persona);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            URI uri = uriBuilder.path(nif).build();
            return Response.created(uri).entity(persona).build();
        } else
            return Response.status(Response.Status.CONFLICT).build();
    }

    @PUT
    @Path("{nif}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces(MediaType.APPLICATION_JSON)
    public Response creaNuevaEntrada(@PathParam("nif") String nif, Persona persona) {
        if(!nif.equals(persona.getNif())) {
            System.out.println(nif);
            System.out.println(persona.getNombre());
            System.out.println(persona.getNif());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            if (personaDAO.actualizaPersona(persona) == true)
                return Response.status(Response.Status.NO_CONTENT).build();
            else {
                personaDAO.nuevaPersona(persona);
                return Response.ok(persona).build();
            }
        }
    }
}

package modelo.dao;

import modelo.datos.Persona;
import modelo.persistencia.BaseDatos;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by oscar on 27/11/14.
 */
@Stateless
public class PersonaDAO {
    public static Persona ENTRADA_NULL = new Persona();
    @Inject
    private BaseDatos bd;

    public void nuevaPersona(Persona persona) {
        bd.nuevaPersona(persona);
    }

    public Persona buscaPersonaPorNIF(String nif) {
        Persona persona = bd.buscaPersonaPorNIF(nif);
        if(persona == BaseDatos.ENTRADA_NULL)
            return ENTRADA_NULL;
        else return persona;
    }

    public Persona[] listaTodasPersonas() {
        return bd.getListaPersonas();
    }

    public boolean creaNuevaEntrada(Persona persona) {
        if(bd.buscaPersonaPorNIF(persona.getNif()) != BaseDatos.ENTRADA_NULL)
            return false;
        else {
            bd.nuevaPersona(persona);
            return true;
        }
    }

    public boolean actualizaPersona(Persona persona) {
        Persona enLista = buscaPersonaPorNIF(persona.getNif());
        if(enLista != ENTRADA_NULL) {
            bd.actualizaPersona(persona);
            return true;
        }
        return false;
    }
}

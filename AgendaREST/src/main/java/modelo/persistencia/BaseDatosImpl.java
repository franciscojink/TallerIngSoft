package modelo.persistencia;

import modelo.datos.Persona;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;


@Singleton
public class BaseDatosImpl implements BaseDatos {
	private Map<String, Persona> mapaPersonas = new HashMap<>();

	public BaseDatosImpl() {
		super();
	}

	public Persona[] getListaPersonas() {
		Persona[] personas = new Persona[mapaPersonas.size()];
		mapaPersonas.values().toArray(personas);
		return personas;
	}
	
	public Persona buscaPersonaPorNIF(String nif) {
		if(mapaPersonas.containsKey(nif))
			return mapaPersonas.get(nif);
		return ENTRADA_NULL;
	}

	public void nuevaPersona(Persona persona) {
		mapaPersonas.put(persona.getNif(), persona);
	}

	public void actualizaPersona(Persona persona) {
		mapaPersonas.put(persona.getNif(), persona);
	}

	public void borraPersona(String nif) {
		Persona entrada = buscaPersonaPorNIF(nif);
		if(entrada != ENTRADA_NULL) {
			mapaPersonas.remove(nif);
		} else System.out.println("No se encontró");
	}
}

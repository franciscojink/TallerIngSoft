package modelo.persistencia;

import es.uji.www.GeneradorDatosINE;
import modelo.datos.Persona;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Singleton
public class BaseDatosImpl implements BaseDatos {
	private final static GeneradorDatosINE generador = new GeneradorDatosINE();
	private Map<String, Persona> mapaPersonas = new HashMap<>();

	public BaseDatosImpl() {
		super();
		iniciaBaseDatos();
	}

	private void iniciaBaseDatos() {
		nuevaPersona(new Persona("Oscar", "Belmonte", "123"));
		nuevaPersona(new Persona("MC", "Erdozain", "222"));
	}

	public void creaEntradasAleatorias(int numeroEntradas) {
		String nombre;
		String apellidos;
		String nif;
		for(int i = 0; i < numeroEntradas; i++) {
			nombre = generador.getNombre();
			apellidos = generador.getApellido();
			nif = generador.getNIF();
			nuevaPersona(new Persona(nombre, apellidos, nif));
		}
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
		Persona enLista = buscaPersonaPorNIF(persona.getNif());
		if(enLista != ENTRADA_NULL) {
			enLista.actualiza(persona);
		}
	}

	public void borraPersona(String nif) {
		Persona entrada = buscaPersonaPorNIF(nif);
		if(entrada != ENTRADA_NULL)
			mapaPersonas.remove(entrada);
	}
}

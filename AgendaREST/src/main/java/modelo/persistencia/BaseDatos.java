package modelo.persistencia;

import modelo.datos.Persona;
//import modelo.datos.utils.ListaPersonas;


public interface BaseDatos {
	Persona ENTRADA_NULL = new Persona();
	Persona[] getListaPersonas();
	Persona buscaPersonaPorNIF(String nif);
	void nuevaPersona(Persona persona);
	void actualizaPersona(Persona entrada);
	void borraPersona(String nif);
}
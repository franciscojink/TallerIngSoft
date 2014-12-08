package modelo.persistencia;

import modelo.datos.Persona;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
public class BaseDatosTest {

    @Test
    public void testGetListaPersonas() throws Exception {
        BaseDatos bd = new BaseDatosImpl();
        Persona primera = mock(Persona.class);
        when(primera.getNif()).thenReturn("1");
        Persona segunda = mock(Persona.class);
        when(segunda.getNif()).thenReturn("2");
        bd.nuevaPersona(primera);
        bd.nuevaPersona(segunda);
        Persona[] personas = new Persona[2];
        Arrays.asList(primera, segunda).toArray(personas);
        assertThat(bd.getListaPersonas(), is(personas));
    }

    @Test
    public void testBuscaPersonaPorNIF() throws Exception {
        BaseDatos bd = new BaseDatosImpl();
        Persona personaMock = mock(Persona.class);
        when(personaMock.getApellidos()).thenReturn("Belmonte");
        when(personaMock.getNombre()).thenReturn("Oscar");
        when(personaMock.getNif()).thenReturn("123");
        bd.nuevaPersona(personaMock);
        Persona encontrada = bd.buscaPersonaPorNIF("123");
        assertThat(encontrada.getNombre(), is("Oscar"));
        assertThat(encontrada.getApellidos(), is("Belmonte"));
        assertThat(encontrada.getNif(), is("123"));
    }

    @Test
    public void testNuevaPersona() throws Exception {
        BaseDatos bd = new BaseDatosImpl();
        Persona personaMock = mock(Persona.class);
        when(personaMock.getApellidos()).thenReturn("Belmonte");
        when(personaMock.getNombre()).thenReturn("Oscar");
        when(personaMock.getNif()).thenReturn("123");
        bd.nuevaPersona(personaMock);
        Persona encontrada = bd.buscaPersonaPorNIF("123");
        assertThat(encontrada.getNombre(), is("Oscar"));
        assertThat(encontrada.getApellidos(), is("Belmonte"));
        assertThat(encontrada.getNif(), is("123"));
    }

    @Test
    public void testActualizaPersona() throws Exception {
        BaseDatos bd = new BaseDatosImpl();
        Persona personaMock = mock(Persona.class);
        when(personaMock.getApellidos()).thenReturn("Belmonte");
        when(personaMock.getNombre()).thenReturn("Oscar");
        when(personaMock.getNif()).thenReturn("123");
        bd.nuevaPersona(personaMock);
        Persona nuevaMock = mock(Persona.class);
        when(nuevaMock.getApellidos()).thenReturn("Belmonte");
        when(nuevaMock.getNombre()).thenReturn("MC");
        when(nuevaMock.getNif()).thenReturn("123");
        bd.actualizaPersona(nuevaMock);
        Persona encontrada = bd.buscaPersonaPorNIF("123");
        assertThat(encontrada.getNombre(), is("MC"));
        assertThat(encontrada.getApellidos(), is("Belmonte"));
        assertThat(encontrada.getNif(), is("123"));
    }

    @Test
    public void testBorraPersona() throws Exception {
        BaseDatos bd = new BaseDatosImpl();
        Persona personaMock = mock(Persona.class);
        when(personaMock.getApellidos()).thenReturn("Belmonte");
        when(personaMock.getNombre()).thenReturn("Oscar");
        when(personaMock.getNif()).thenReturn("123");
        bd.nuevaPersona(personaMock);
        Persona encontrada = bd.buscaPersonaPorNIF("123");
        assertThat(encontrada.getNombre(), is("Oscar"));
        assertThat(encontrada.getApellidos(), is("Belmonte"));
        assertThat(encontrada.getNif(), is("123"));
        bd.borraPersona("123");
        encontrada = bd.buscaPersonaPorNIF("123");
        assertThat(encontrada, is(BaseDatos.ENTRADA_NULL));
    }
}
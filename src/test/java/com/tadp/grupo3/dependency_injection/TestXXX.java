package com.tadp.grupo3.dependency_injection;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import com.tadp.grupo3.dependency_injection.exceptions.MasDeUnBindingException;
import com.tadp.grupo3.dependency_injection.exceptions.NoExisteBindingException;
import com.tadp.grupo3.dependency_injection.fixture_peliculas.EnMemoriaPeliculasHome;
import com.tadp.grupo3.dependency_injection.fixture_peliculas.PeliculasController;
import com.tadp.grupo3.dependency_injection.fixture_peliculas.PeliculasHome;
import com.tadp.grupo3.dependency_injection.fixture_peliculas.PersonaHome;
import com.tadp.grupo3.dependency_injection.fixture_peliculas.SqlPeliculasHome;
import com.tadp.grupo3.dependency_injection.fixture_perros.Bulldog;
import com.tadp.grupo3.dependency_injection.fixture_perros.CorreaDePerro;
import com.tadp.grupo3.dependency_injection.fixture_perros.CorreaMetalica;
import com.tadp.grupo3.dependency_injection.fixture_perros.Perro;

public class TestXXX {

	private Contexto contexto;

	@Before
	public void setUp() {
		this.contexto = new Contexto();
	}
	
	@Test
	public void obtenerInstancia_crea_un_objeto_del_tipo_especificado_en_el_binding() {
		this.contexto.agregarBinding(PeliculasHome.class, EnMemoriaPeliculasHome.class);
		PeliculasHome home = contexto.obtenerInstancia(PeliculasHome.class);
		
		assertThat(home, instanceOf(EnMemoriaPeliculasHome.class));
	}

	@Test
	public void obtenerInstancia_crea_objetos_de_los_tipos_especificados_en_los_bindings() {
		this.contexto.agregarBinding(PeliculasHome.class, SqlPeliculasHome.class);
		this.contexto.agregarBinding(Perro.class, Bulldog.class);

		Perro perro = contexto.obtenerInstancia(Perro.class);
		PeliculasHome home = contexto.obtenerInstancia(PeliculasHome.class);
		
		assertThat(home, instanceOf(SqlPeliculasHome.class));
		assertThat(perro, instanceOf(Bulldog.class));
	}
	
	@Test(expected=NoExisteBindingException.class)
	public void obtenerInstancia_explota_si_no_hay_bindings_para_el_tipo_solicitado() {
		this.contexto.obtenerInstancia(PersonaHome.class);
	}
	
	@Test(expected=MasDeUnBindingException.class)
	public void obtenerInstancia_explota_si_hay_mas_de_un_binding_para_el_tipo_solicitado() {
		this.contexto.agregarBinding(PeliculasHome.class, EnMemoriaPeliculasHome.class);
		this.contexto.agregarBinding(PeliculasHome.class, SqlPeliculasHome.class);
		
		this.contexto.obtenerInstancia(PeliculasHome.class);
	}
	
	@Test
	public void obtenerObjetoPrimitivoPara_retorna_el_objeto_configurado_para_el_scope_dado() {
		contexto.agregarBindingInstancia(SqlPeliculasHome.class, "...cadena de conexion a SQL...");
		contexto.agregarBindingInstancia(EnMemoriaPeliculasHome.class, "0x88AB82");
		
		String cadenaConexion = contexto.obtenerObjetoPrimitivoPara(SqlPeliculasHome.class, String.class);
		String offset = contexto.obtenerObjetoPrimitivoPara(EnMemoriaPeliculasHome.class, String.class);
		
		assertEquals("...cadena de conexion a SQL...", cadenaConexion);
		assertEquals("0x88AB82", offset);
	}
	@Test
	public void instanciarUnObjetoPorConstructor(){
		
		contexto.agregarBinding(PeliculasHome.class, SqlPeliculasHome.class);

		PeliculasController unController = contexto.obtenerInstancia(PeliculasController.class, new PorConstructorStrategy());
		
		assertTrue(unController.home instanceof SqlPeliculasHome);
	}
	
	@Test(expected=FaltaBindingException.class)
	public void comprobarInstanciacionDeParametro(){
		contexto.comprobarIntanciacionDelParametro(PeliculasController.class);
	}
//	
//	@Test
//	public void test5() {
//		this.contexto.agregarBinding(Perro.class, Bulldog.class);
//		this.contexto.agregarBinding(CorreaDePerro.class, CorreaMetalica.class);
//		
//		CorreaDePerro correa = this.contexto.obtenerInstancia(CorreaDePerro.class);
//		
//		assertNotNull(correa);
//		assertThat(correa, instanceOf(CorreaMetalica.class));
//
//		assertNotNull(correa.getPerro());
//		assertThat(correa.getPerro(), instanceOf(Bulldog.class));
//	}

}

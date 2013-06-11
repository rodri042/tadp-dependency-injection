package com.tadp.grupo3.dependency_injection.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.tadp.grupo3.dependency_injection.exceptions.NoExisteBindingException;
import com.tadp.grupo3.dependency_injection.exceptions.YaExisteBindingException;
import com.tadp.grupo3.dependency_injection.fixture.EnMemoriaPeliculasHome;
import com.tadp.grupo3.dependency_injection.fixture.MongoDbPeliculasHome;
import com.tadp.grupo3.dependency_injection.fixture.PeliculasHome;
import com.tadp.grupo3.dependency_injection.framework.Inyectador;

public class InyectadorTest {
	private Inyectador contexto;
	
	@Before
	public void setUp() {
		this.contexto = new Inyectador();
	}
	
	@Test
	public void obtenerObjeto_crea_un_objeto_del_tipo_especificado_en_el_binding() {
		this.contexto.agregarBinding("PeliculasHome", EnMemoriaPeliculasHome.class);
		
		PeliculasHome elHome = (PeliculasHome) this.contexto.obtenerObjeto("PeliculasHome");
		assertTrue(elHome instanceof EnMemoriaPeliculasHome);
	}
	
	@Test(expected = NoExisteBindingException.class)
	public void obtenerObjeto_falla_al_pedir_objeto_no_bindeado() {
		this.contexto.obtenerObjeto("PeliculasHome");
	}
	
	@Test(expected = YaExisteBindingException.class)
	public void agregarBinding_falla_al_bindear_objeto_ya_bindeado() {
		this.contexto.agregarBinding("PeliculasHome", EnMemoriaPeliculasHome.class);
		this.contexto.agregarBinding("PeliculasHome", MongoDbPeliculasHome.class);
	}
}

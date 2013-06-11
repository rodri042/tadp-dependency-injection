package com.tadp.grupo3.dependency_injection.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.tadp.grupo3.dependency_injection.exceptions.NoExisteBindingException;
import com.tadp.grupo3.dependency_injection.exceptions.YaExisteBindingException;
import com.tadp.grupo3.dependency_injection.fixture.EnMemoriaPeliculasHome;
import com.tadp.grupo3.dependency_injection.fixture.MailSender;
import com.tadp.grupo3.dependency_injection.fixture.MongoDbLogger;
import com.tadp.grupo3.dependency_injection.fixture.MongoDbPeliculasHome;
import com.tadp.grupo3.dependency_injection.fixture.PeliculasHome;
import com.tadp.grupo3.dependency_injection.framework.ArgumentoPorId;
import com.tadp.grupo3.dependency_injection.framework.InyectadorPorAccessors;
import com.tadp.grupo3.dependency_injection.framework.InyectadorPorConstructor;

public class InyectadorTest {
	@Test
	public void obtenerObjeto_crea_un_objeto_del_tipo_especificado_en_el_binding() {
		InyectadorPorConstructor contexto = new InyectadorPorConstructor();
		
		contexto.agregarBinding("PeliculasHome", EnMemoriaPeliculasHome.class);
		
		PeliculasHome elHome = (PeliculasHome) contexto.obtenerObjeto("PeliculasHome");
		assertTrue(elHome instanceof EnMemoriaPeliculasHome);
	}
	
	@Test(expected = NoExisteBindingException.class)
	public void obtenerObjeto_falla_al_pedir_objeto_no_bindeado() {
		InyectadorPorConstructor contexto = new InyectadorPorConstructor();
		
		contexto.obtenerObjeto("PeliculasHome");
	}
	
	@Test(expected = YaExisteBindingException.class)
	public void agregarBinding_falla_al_bindear_objeto_ya_bindeado() {
		InyectadorPorConstructor contexto = new InyectadorPorConstructor();
		
		contexto
			.agregarBinding("PeliculasHome", EnMemoriaPeliculasHome.class)
			.agregarBinding("PeliculasHome", MongoDbPeliculasHome.class);
	}
	
	@Test
	public void obtenerObjeto_crea_un_objeto_por_constructor() {
		InyectadorPorConstructor contexto = new InyectadorPorConstructor();
		
		contexto.agregarBinding("MailSender", MailSender.class);
		contexto
			.agregarArgumento("MailSender", "algo@algo.com")
			.agregarArgumento("MailSender", "unacontraseña123secreta")
			.agregarArgumento("MailSender", "smtp.algo.com")
			.agregarArgumento("MailSender", 2013);
		
		MailSender elSender = (MailSender) contexto.obtenerObjeto("MailSender");
		assertTrue(elSender instanceof MailSender);
	}
	
	@Test
	public void obtenerObjeto_crea_un_objeto_por_constructor_bindeando_ids() {
		InyectadorPorConstructor contexto = new InyectadorPorConstructor();
		
		contexto
			.agregarBinding("PeliculasHome", MongoDbPeliculasHome.class)
			.agregarBinding("Logger", MongoDbLogger.class);
			
		contexto.agregarArgumento("PeliculasHome", new ArgumentoPorId("Logger"));
		
		PeliculasHome elHome = (PeliculasHome) contexto.obtenerObjeto("PeliculasHome");
		assertTrue(elHome instanceof MongoDbPeliculasHome);
	}
	
	@Test
	public void obtenerObjeto_crea_un_objeto_por_accessors() {
		InyectadorPorAccessors contexto = new InyectadorPorAccessors();
		
		contexto.agregarBinding("MailSender", MailSender.class);
		contexto
			.agregarAtributo("usuario", "papa@frita.net")
			.agregarAtributo("passworrd", "notedoymiclave")
			.agregarAtributo("smtp", "smtp.gmail.com")
			.agregarAtributo("puerto", 3389);
		
		MailSender unMailSender = (MailSender) contexto.obtenerObjeto("MailSender");
		assertEquals("papa@frita.net", unMailSender.getUsuario());
		assertEquals("notedoymiclave", unMailSender.getPassword());
		assertEquals("smtp.gmail.com", unMailSender.getSmtp());
		assertEquals((Integer) 3389, unMailSender.getPuerto());
	}
	
	/* Hacer el test de PeliculasHome y MdxPeliculasHome */
}

package com.tadp.grupo3.dependency_injection.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.tadp.grupo3.dependency_injection.exceptions.NoExisteBindingException;
import com.tadp.grupo3.dependency_injection.exceptions.YaExisteBindingException;
import com.tadp.grupo3.dependency_injection.fixture.CineController;
import com.tadp.grupo3.dependency_injection.fixture.EnMemoriaPeliculasHome;
import com.tadp.grupo3.dependency_injection.fixture.MailSender;
import com.tadp.grupo3.dependency_injection.fixture.MdxPeliculasHome;
import com.tadp.grupo3.dependency_injection.fixture.MongoDbLogger;
import com.tadp.grupo3.dependency_injection.fixture.MongoDbPeliculasHome;
import com.tadp.grupo3.dependency_injection.fixture.PeliculasHome;
import com.tadp.grupo3.dependency_injection.framework.Inyectador;
import com.tadp.grupo3.dependency_injection.framework.ObjetoPorId;

public class InyectadorTest {
	Inyectador contexto;
	
	@Before
	public void setUp() {
		this.contexto = new Inyectador();
	}
	
	@Test
	public void obtenerObjeto_crea_un_objeto_del_tipo_especificado_en_el_binding() {
		this.contexto
			.agregar("PeliculasHome", EnMemoriaPeliculasHome.class)
			.usarConstructor();
		
		PeliculasHome elHome = (PeliculasHome) contexto.obtenerObjeto("PeliculasHome");
		assertTrue(elHome instanceof EnMemoriaPeliculasHome);
	}
	
	@Test(expected = NoExisteBindingException.class)
	public void obtenerObjeto_falla_al_pedir_objeto_no_bindeado() {
		contexto.obtenerObjeto("PeliculasHome");
	}
	
	@Test(expected = YaExisteBindingException.class)
	public void agregarBinding_falla_al_bindear_objeto_ya_bindeado() {
		contexto
			.agregar("PeliculasHome", EnMemoriaPeliculasHome.class)
			.usarConstructor();
		
		contexto
			.agregar("PeliculasHome", MongoDbPeliculasHome.class)
			.usarConstructor();
	}
	
	@Test
	public void obtenerObjeto_crea_un_objeto_por_constructor() {
		contexto
			.agregar("MailSender", MailSender.class)
			.usarConstructor("algo@algo.com", "unacontraseña123secreta", "smtp.algo.com", 2013);
		
		MailSender elSender = (MailSender) contexto.obtenerObjeto("MailSender");
		assertTrue(elSender instanceof MailSender);
	}
	
	@Test
	public void obtenerObjeto_crea_un_objeto_por_constructor_bindeando_ids() {
		contexto
			.agregar("PeliculasHome", MongoDbPeliculasHome.class)
			.usarConstructor(new ObjetoPorId("Logger"));
		
		contexto
			.agregar("Logger", MongoDbLogger.class)
			.usarConstructor();
			
		PeliculasHome elHome = (PeliculasHome) contexto.obtenerObjeto("PeliculasHome");
		assertTrue(elHome instanceof MongoDbPeliculasHome);
	}
	
	@Test
	public void obtenerObjeto_crea_un_objeto_por_accessors() {
		contexto
			.agregar("MailSender", MailSender.class)
			.agregarAtributo("usuario", "papa@frita.net")
			.agregarAtributo("password", "notedoymiclave")
			.agregarAtributo("smtp", "smtp.gmail.com")
			.agregarAtributo("puerto", 3389);
		
		MailSender unMailSender = (MailSender) contexto.obtenerObjeto("MailSender");
		assertEquals("papa@frita.net", unMailSender.getUsuario());
		assertEquals("notedoymiclave", unMailSender.getPassword());
		assertEquals("smtp.gmail.com", unMailSender.getSmtp());
		assertEquals((Integer) 3389, unMailSender.getPuerto());
	}
	
	@Test
	public void obtenerObjeto_crea_un_objeto_por_accessors_a_varios_niveles() {
		contexto
			.agregar("Logger", MongoDbLogger.class)
			.usarConstructor();
		
		contexto
			.agregar("PeliculasHome", MdxPeliculasHome.class)
			.agregarAtributo("logger", new ObjetoPorId("Logger"));
		
		contexto
			.agregar("CineController", CineController.class)
			.agregarAtributo("peliculasHome", new ObjetoPorId("PeliculasHome"));
		
		CineController unController = (CineController) contexto.obtenerObjeto("CineController");
		
		assertTrue(unController.getPeliculasHome() instanceof MdxPeliculasHome);
		assertTrue(unController.getPeliculasHome().getLogger() instanceof MongoDbLogger);
	}
}

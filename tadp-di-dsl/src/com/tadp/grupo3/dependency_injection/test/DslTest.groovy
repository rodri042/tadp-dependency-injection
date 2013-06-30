package com.tadp.grupo3.dependency_injection.test;
import com.tadp.grupo3.dependency_injection.dsl.*
import static com.tadp.grupo3.dependency_injection.dsl.Dsl.*
import com.tadp.grupo3.dependency_injection.framework.*
import com.tadp.grupo3.dependency_injection.fixture.*

import static org.junit.Assert.*;

import org.junit.Test;

public class DslTest {
	def framework
	static {
		new Dsl()
	}
	DslTest() {
		this.framework = new Inyectador()
	}
	
	@Test
	public void "El dsl puede configurar un objeto por constructor"() {
		framework.dijeramosQue {
			"MailSender" es un MailSender.class con {
				constructor "mail@password.net",
							"unPassword",
							"unsmtp.dominio.com.ar",
							3389
			}
		}
		
		def sender = framework.obtenerObjeto("MailSender")
		assertTrue(sender instanceof MailSender)
	}
	
	@Test
	public void "El dsl puede configurar un objeto por setters"() {
		framework.dijeramosQue {
			"MailSender" es un MailSender.class con {
				un "usuario" igualA "algo@algo.com"
				un "password" igualA "unPassword"
				un "puerto" igualA 3389
			}
		}
		
		def sender = framework.obtenerObjeto("MailSender")
		assertTrue(sender instanceof MailSender)
		assertEquals("algo@algo.com", sender.getUsuario())
		assertEquals("unPassword", sender.getPassword())
		assertEquals(3389, sender.getPuerto())
	}
	
	@Test
	public void "El dsl funciona usando objetos por Id"() {
		framework.dijeramosQue {
			"PeliculasHome" es un MongoDbPeliculasHome.class con {
				constructor(new ObjetoPorId("Logger"))
			}
			"Logger" es un MongoDbLogger.class con {
				constructor()
			}
		}
		
		def home = framework.obtenerObjeto("PeliculasHome")
		assertTrue(home instanceof MongoDbPeliculasHome)
	}
	
	@Test
	public void "El dsl funciona usando objetos por Id mediante 'referenciando'"() {
		framework.dijeramosQue {
			"Logger" es un MongoDbLogger.class con {
				constructor()
			}
			"PeliculasHome" es un MdxPeliculasHome.class con {
				un "logger" referenciando "Logger"
			}
			"CineController" es un CineController.class con {
				un "peliculasHome" referenciando "PeliculasHome"
			}
		}
		
		def cine = framework.obtenerObjeto("CineController")
		assertTrue(cine.getPeliculasHome() instanceof MdxPeliculasHome);
		assertTrue(cine.getPeliculasHome().getLogger() instanceof MongoDbLogger);
	}
}

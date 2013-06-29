package com.tadp.grupo3.dependency_injection.test;
import com.tadp.grupo3.dependency_injection.dsl.*
import com.tadp.grupo3.dependency_injection.framework.*
import com.tadp.grupo3.dependency_injection.fixture.*
import static org.junit.Assert.*;
import org.junit.Test;

public class DslTest {
	@Test
	public void test1() {
		new Dsl()
		
		def framework = new Inyectador()
		framework.dijeramosQue {
			"MailSender" esUn MailSender.class con {
				un "usuario" igualA "algo@algo.com"
				un "password" igualA "unPassword"
				un "puerto" igualA 3389
			}
		}
		
	}
}

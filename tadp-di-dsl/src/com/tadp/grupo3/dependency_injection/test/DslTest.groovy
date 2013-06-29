package com.tadp.grupo3.dependency_injection.test;
import static org.junit.Assert.*;
import com.tadp.grupo3.dependency_injection.dsl.Inyectador
import org.junit.Test;

public class DslTest {

	@Test
	public void test1() {
		def framework = new Inyectador()
		def algo = inyectador.dijeramosQue {
			"MailSender" /* esUn Inyectador.class */
		}
		assertEquals(3, algo)
	}

}

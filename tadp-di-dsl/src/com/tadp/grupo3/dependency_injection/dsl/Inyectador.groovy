package com.tadp.grupo3.dependency_injection.dsl;

public class Inyectador {
	def agregar(string, clase) {
		string + clase.getName()
	}
}

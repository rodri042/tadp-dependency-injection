package com.tadp.grupo3.dependency_injection.dsl;

@Category(Object)
public class BindingBuilderDsl {
	def con (bloque) {
		this.with bloque
	}
	
	def un(nombre) {
		new IdAtributo(nombre, this)
	}
	
	def constructor(Object... argumentos) {
		this.usarConstructor(argumentos)
	}
}

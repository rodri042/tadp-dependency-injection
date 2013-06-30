package com.tadp.grupo3.dependency_injection.dsl

class IdAtributo {
	def nombre
	def bindingBuilder
	
	IdAtributo(String nombre, bindingBuilder) {
		this.nombre = nombre
		this.bindingBuilder = bindingBuilder
	}
	
	def igualA(valor) {
		this.bindingBuilder.agregarAtributo(this.nombre, valor)
	}
}

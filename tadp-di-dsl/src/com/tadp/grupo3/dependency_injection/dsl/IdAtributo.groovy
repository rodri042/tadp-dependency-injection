package com.tadp.grupo3.dependency_injection.dsl
import com.tadp.grupo3.dependency_injection.framework.ObjetoPorId


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
	
	def referenciando(otroId) {
		this.bindingBuilder.agregarAtributo(this.nombre, new ObjetoPorId(otroId))
	}
}

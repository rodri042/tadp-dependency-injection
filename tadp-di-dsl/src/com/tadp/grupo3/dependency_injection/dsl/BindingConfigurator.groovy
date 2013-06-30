package com.tadp.grupo3.dependency_injection.dsl

class BindingConfigurator {
	def bindingBuilder
	
	BindingConfigurator(bindingBuilder) {
		this.bindingBuilder = bindingBuilder
	}
	
	def un(nombre) {
		new IdAtributo(nombre, this)
	}
}

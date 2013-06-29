package com.tadp.grupo3.dependency_injection.dsl

class IdBinding {
	def nombre
	def framework
	
	IdBinding(String nombre, framework) {
		this.nombre = nombre
		this.framework = framework
	}
	
	def esUn(clase) {
		this.framework.agregar(this.nombre, clase)
	}
}

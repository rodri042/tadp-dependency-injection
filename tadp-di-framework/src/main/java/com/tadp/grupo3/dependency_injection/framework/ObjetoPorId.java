package com.tadp.grupo3.dependency_injection.framework;

//Representa una referencia a un objeto por su id bindeado
public class ObjetoPorId {
	private String id;
	
	public ObjetoPorId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}

	public Object obtenerObjetoReal(Inyectador framework) {
		return framework.obtenerObjeto(this.getId());
	}
}

package com.tadp.grupo3.dependency_injection.framework;

//Representa un binding a una clase
public abstract class Binding {
	//Instancia el objeto que bindea
	abstract Object instanciar(Inyectador framework);
	
	//Obtiene el objeto real, si el objeto dado es una referencia a otro binding
	protected Object procesarObjetoPorId(Inyectador framework, Object objeto) {
		if (objeto.getClass() == ObjetoPorId.class) {
			ObjetoPorId objetoPorId = (ObjetoPorId) objeto;
			
			return framework.obtenerObjeto(objetoPorId.getId());
		} else
			return objeto;
	}
}

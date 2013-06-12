package com.tadp.grupo3.dependency_injection.framework;

import java.util.HashMap;
import java.util.Map;

import com.tadp.grupo3.dependency_injection.exceptions.NoExisteBindingException;
import com.tadp.grupo3.dependency_injection.exceptions.YaExisteBindingException;

//Es el framework genérico
public abstract class Inyectador {
	protected Map<String, Binding> bindings;
	
	public Inyectador() {
		this.bindings = new HashMap<String, Binding>();
	}
	
	//Agrega un binding a su colección de bindings
	public Inyectador agregarBinding(String id, Class<?> clase) {
		if (this.bindings.containsKey(id))
			throw new YaExisteBindingException();
		
		this.bindings.put(id, this.nuevoBindingPara(id, clase));
		return this;
	}
	
	//Delega a las subclases la instanciación del tipo de binding
	protected abstract Binding nuevoBindingPara(String id, Class<?> clase);

	//Crea un objeto a partir de un id de binding
	public Object obtenerObjeto(String id) {
		return this
			.obtenerBinding(id)
			.instanciar(this);
	}
	
	//Obtiene el binding correspondiente a partir del id
	protected Binding obtenerBinding(String id) {
		Binding binding = this.bindings.get(id);
		if (binding == null)
			throw new NoExisteBindingException();
		return binding;
	}
}

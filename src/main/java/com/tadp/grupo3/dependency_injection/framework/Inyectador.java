package com.tadp.grupo3.dependency_injection.framework;

import java.util.HashMap;
import java.util.Map;

import com.tadp.grupo3.dependency_injection.exceptions.NoExisteBindingException;
import com.tadp.grupo3.dependency_injection.exceptions.YaExisteBindingException;

//Es el framework
public class Inyectador {
	protected Map<String, Binding> bindings;
	
	public Inyectador() {
		this.bindings = new HashMap<String, Binding>();
	}
	
	//Agrega un binding a su colección de bindings
	public BindingBuilder agregar(String id, Class<?> clase) {
		if (this.bindings.containsKey(id))
			throw new YaExisteBindingException();
		
		return new BindingBuilder(id, clase, this);
	}
	
	public Inyectador agregar(String id, Binding binding) {
		this.bindings.put(id, binding);
		return this;
	}

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

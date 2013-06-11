package com.tadp.grupo3.dependency_injection.framework;

import java.util.HashMap;
import java.util.Map;

import com.tadp.grupo3.dependency_injection.exceptions.NoExisteBindingException;
import com.tadp.grupo3.dependency_injection.exceptions.YaExisteBindingException;

public class Inyectador {
	private Map<String, Binding> bindings;
	
	public Inyectador() {
		this.bindings = new HashMap<String, Binding>();
	}
	
	public Inyectador agregarBinding(String id, Class<?> clase) {
		if (this.bindings.containsKey(id))
			throw new YaExisteBindingException();
		
		Binding binding = new Binding(clase);
		this.bindings.put(id, binding);
		return this;
	}
	
	public Inyectador agregarArgumento(String idBinding, Object argumento) {
		Binding binding = this.obtenerBinding(idBinding);
		binding.agregarArgumento(argumento);
		return this;
	}

	public Object obtenerObjeto(String id) {
		return this
			.obtenerBinding(id)
			.instanciar(this);
	}
	
	private Binding obtenerBinding(String id) {
		Binding binding = this.bindings.get(id);
		if (binding == null)
			throw new NoExisteBindingException();
		return binding;
	}
}

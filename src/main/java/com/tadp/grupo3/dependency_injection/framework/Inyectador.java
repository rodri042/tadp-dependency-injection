package com.tadp.grupo3.dependency_injection.framework;

import java.util.HashMap;
import java.util.Map;

import com.tadp.grupo3.dependency_injection.exceptions.NoExisteBindingException;
import com.tadp.grupo3.dependency_injection.exceptions.YaExisteBindingException;

public abstract class Inyectador {
	protected Map<String, BindingPorConstructor> bindings;
	
	public Inyectador() {
		this.bindings = new HashMap<String, BindingPorConstructor>();
	}
	
	public Inyectador agregarBinding(String id, Class<?> clase) {
		if (this.bindings.containsKey(id))
			throw new YaExisteBindingException();
		
		BindingPorConstructor binding = new BindingPorConstructor(clase);
		this.bindings.put(id, binding);
		return this;
	}

	public Object obtenerObjeto(String id) {
		return this
			.obtenerBinding(id)
			.instanciar(this);
	}
	
	protected BindingPorConstructor obtenerBinding(String id) {
		BindingPorConstructor binding = this.bindings.get(id);
		if (binding == null)
			throw new NoExisteBindingException();
		return binding;
	}
}

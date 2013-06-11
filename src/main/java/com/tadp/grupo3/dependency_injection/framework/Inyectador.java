package com.tadp.grupo3.dependency_injection.framework;

import java.util.HashMap;
import java.util.Map;

public class Inyectador {
	private Map<String, Binding> bindings;
	
	public Inyectador() {
		this.bindings = new HashMap<String, Binding>();
	}
	
	public void agregarBinding(String id, Class<?> tipo) {
		Binding binding = new Binding(id, tipo);
		bindings.put(id, binding);
	}

	public Object obtenerObjeto(String id) {
		Binding binding = this.bindings.get(id);
		return binding.instanciar();
	}
}

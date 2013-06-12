package com.tadp.grupo3.dependency_injection.framework;

import java.util.HashMap;
import java.util.Map;

import com.tadp.grupo3.dependency_injection.exceptions.NoExisteBindingException;
import com.tadp.grupo3.dependency_injection.exceptions.YaExisteBindingException;

public abstract class Inyectador {
	protected Map<String, Binding> bindings;
	
	public Inyectador() {
		this.bindings = new HashMap<String, Binding>();
	}
	
	public Inyectador agregarBinding(String id, Class<?> clase) {
		if (this.bindings.containsKey(id))
			throw new YaExisteBindingException();
		
		this.bindings.put(id, this.nuevoBindingPara(id, clase));
		return this;
	}
	protected abstract Binding nuevoBindingPara(String id, Class<?> clase);

	public Object obtenerObjeto(String id) {
		return this
			.obtenerBinding(id)
			.instanciar(this);
	}
	
	protected Binding obtenerBinding(String id) {
		Binding binding = this.bindings.get(id);
		if (binding == null)
			throw new NoExisteBindingException();
		return binding;
	}
}

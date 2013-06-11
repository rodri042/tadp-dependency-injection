package com.tadp.grupo3.dependency_injection.framework;

public class InyectadorPorConstructor extends Inyectador {
	public InyectadorPorConstructor agregarArgumento(String idBinding, Object argumento) {
		BindingPorConstructor binding = (BindingPorConstructor) this.obtenerBinding(idBinding);
		binding.agregarArgumento(argumento);
		return this;
	}
	
	protected void nuevoBindingPara(String id, Class<?> clase) {
		BindingPorConstructor binding = new BindingPorConstructor(clase);
		this.bindings.put(id, binding);
	}
}

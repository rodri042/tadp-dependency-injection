package com.tadp.grupo3.dependency_injection.framework;

//Framework de inyección de dependencias por constructor
public class InyectadorPorConstructor extends Inyectador {
	//Delega al binding la agregación del argumento
	public InyectadorPorConstructor agregarArgumento(String idBinding, Object argumento) {
		BindingPorConstructor binding = (BindingPorConstructor) this.obtenerBinding(idBinding);
		binding.agregarArgumento(argumento);
		return this;
	}
	
	protected Binding nuevoBindingPara(String id, Class<?> clase) {
		return new BindingPorConstructor(clase);
	}
}

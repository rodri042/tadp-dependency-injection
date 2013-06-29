package com.tadp.grupo3.dependency_injection.framework;

import java.lang.reflect.Method;
import com.tadp.grupo3.dependency_injection.exceptions.NoExisteSetterException;
import com.tadp.grupo3.dependency_injection.exceptions.TipoDeBindingInconsistenteException;

//Interfaz para configurar los bindings
public class BindingBuilder {
	String id;
	Class<?> clase;
	Inyectador framework;
	BindingPorAccessor bindingAccessors;
	
	public BindingBuilder(String id, Class<?> clase, Inyectador framework) {
		this.id = id;
		this.clase = clase;
		this.framework = framework;
	}
	
	public void usarConstructor(Object... argumentos) {
		if (this.bindingAccessors != null)
			throw new TipoDeBindingInconsistenteException();
		
		 BindingPorConstructor binding = new BindingPorConstructor(this.clase);
		 this.framework.agregar(this.id, binding);
		 
		 for(Object argumento : argumentos)
		 	binding.agregarArgumento(argumento);
	}
	
	public BindingBuilder agregarAtributo(String unAtributo, Object unValor) {
		if (this.bindingAccessors == null) {
			this.bindingAccessors = new BindingPorAccessor(this.clase);
			this.framework.agregar(this.id, this.bindingAccessors);
		}
		
		try {
			Method[] metodos = clase.getMethods();
			for (Method unMetodo : metodos) {
				if (this.esElSetterDe(unMetodo, unAtributo)) {
					this.bindingAccessors.addSetter(new Setter(unMetodo, unValor));
					return this;
				}
			}
			
			throw new NoExisteSetterException();
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		}
	}
	
	//Determina si un método es el setter de un atributo
	private boolean esElSetterDe(Method unMetodo, String unAtributo) {
		return this.esUnSetter(unMetodo) && unMetodo.getName().equals("set" + this.capitalizarString(unAtributo));
	}
	
	//Deja en mayúsculas la primer letra del string
	private String capitalizarString(String string) {
		return Character.toUpperCase(string.charAt(0)) + string.substring(1);
	}

	//Determina si un método determinado es un setter
	private boolean esUnSetter(Method unMetodo) {
		return unMetodo.getName().startsWith("set");
	}
}

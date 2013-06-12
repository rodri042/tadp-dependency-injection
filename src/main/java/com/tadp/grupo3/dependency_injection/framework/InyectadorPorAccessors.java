package com.tadp.grupo3.dependency_injection.framework;

import java.lang.reflect.Method;

import com.tadp.grupo3.dependency_injection.exceptions.NoExisteSetterException;

//Framework de inyección de dependencia por setters
//Los objetos a instanciar deben tener un constructor vacío
public class InyectadorPorAccessors extends Inyectador {
	protected Binding nuevoBindingPara(String id, Class<?> clase) {
		return new BindingPorAccessor(clase);
	}
	
	//Agrega un atributo a ser seteado luego de la instanciación
	public InyectadorPorAccessors agregarAtributo(String idBinding, String unAtributo, Object unValor) {
		try {			
			BindingPorAccessor unBindingPorAccesor = (BindingPorAccessor) this.obtenerBinding(idBinding);
			Class<?> clase = unBindingPorAccesor.getClase();

			Method[] metodos = clase.getMethods();
			for (Method unMetodo : metodos) {
				if (this.esElSetterDe(unMetodo, unAtributo)) {
					unBindingPorAccesor.addSetter(new Setter(unMetodo, unValor));
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
	public boolean esElSetterDe(Method unMetodo, String unAtributo) {
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

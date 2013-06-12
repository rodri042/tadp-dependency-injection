package com.tadp.grupo3.dependency_injection.framework;

import java.lang.reflect.Method;

public class InyectadorPorAccessors extends Inyectador {
	protected Binding nuevoBindingPara(String id, Class<?> clase) {
		return new BindingPorAccessor(clase);
	}
	
	public InyectadorPorAccessors agregarAtributo(String idBinding, String unAtributo, Object unValor) {
		try {			
			BindingPorAccessor unBindingPorAccesor = (BindingPorAccessor) this.obtenerBinding(idBinding);
			Class<?> clase = unBindingPorAccesor.getClase();

			Method[] metodos = clase.getMethods();
			for (Method unMetodo : metodos) {
				if (this.esElSetterDe(unMetodo, unAtributo)) {
					unBindingPorAccesor.addSetter(new Setter(unMetodo, unValor));
					break;
				}
			}
			
			return this;
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean esElSetterDe(Method unMetodo, String unAtributo) {
		return this.esUnSetter(unMetodo) && unMetodo.getName().equals("set" + this.capitalizarString(unAtributo));
	}
	
	private String capitalizarString(String line) {
		return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}

	private boolean esUnSetter(Method unMetodo) {
		return unMetodo.getName().startsWith("set");
	}
}

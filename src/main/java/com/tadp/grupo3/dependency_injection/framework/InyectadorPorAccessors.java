package com.tadp.grupo3.dependency_injection.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InyectadorPorAccessors extends Inyectador {

	public InyectadorPorAccessors agregarAtributo(String idBinding, String nombre, Object valor) {

		try {

			//TODO: Arreglar esto de instanciar un nuevo objeto cada vez que le agrego un atributo.
			// Deberia solo instanciar una vez. Y devolver siempre la misma instancias.
			
			Object unObjeto = this.obtenerObjeto(idBinding);
			Class<?> clase = unObjeto.getClass();

			Method[] metodos = clase.getMethods();

			for (Method unMetodo : metodos) {
				if (EsElSetterBuscado(unMetodo, nombre)) {
					unMetodo.invoke(unObjeto, valor);
					break;
				}

			}
			
			return this;

		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean EsElSetterBuscado(Method unMetodo, String nombre) {

		return (this.esUnSetter(unMetodo) && unMetodo.getName().equals(
				"set" + this.capitalize(nombre)));
	}

	private boolean esUnSetter(Method unMetodo) {
		return unMetodo.getName().startsWith("set");
	}

	protected void nuevoBindingPara(String id, Class<?> clase) {

		BindingPorAccessor unBindingPorAccesor = new BindingPorAccessor(clase);
		this.bindings.put(id, unBindingPorAccesor);

	}

	private String capitalize(String line) {
		return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}
}

package com.tadp.grupo3.dependency_injection.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.tadp.grupo3.dependency_injection.exceptions.NingunConstructorValidoException;
import com.tadp.grupo3.dependency_injection.exceptions.SeRompioTodoException;

public class BindingPorConstructor implements Binding {
	private Class<?> clase;
	private List<Object> argumentos;

	public BindingPorConstructor(Class<?> clase) {
		this.clase = clase;
		this.argumentos = new ArrayList<Object>();
	}

	public void agregarArgumento(Object argumento) {
		this.argumentos.add(argumento);
	}

	public Object instanciar(Inyectador framework) {
		Object[] argumentos = this.procesarBindings(framework);
		
		Constructor<?> constructor = this.elegirConstructor(argumentos);
		try {
			return constructor.newInstance(argumentos);
		} catch (InstantiationException e) {
			throw new SeRompioTodoException();
		} catch (IllegalAccessException e) {
			throw new SeRompioTodoException();
		} catch (IllegalArgumentException e) {
			throw new SeRompioTodoException();
		} catch (InvocationTargetException e) {
			throw new SeRompioTodoException();
		}
	}

	private Object[] procesarBindings(Inyectador framework) {
		Object[] argumentos = this.argumentos.toArray();
		
		for (int i=0; i < argumentos.length; i++) {
			if (argumentos[i].getClass() == ArgumentoPorId.class) {
				ArgumentoPorId argumento = (ArgumentoPorId) argumentos[i];
				
				argumentos[i] = framework.obtenerObjeto(argumento.getId());
			}
		}
		return argumentos;
	}

	private Constructor<?> elegirConstructor(Object[] argumentos) {
		Constructor<?>[] constructores = this.clase.getConstructors();

		for (Constructor<?> constructor : constructores) {
			if (this.puedoUsarElConstructor(constructor, argumentos))
				return constructor;
		}
		throw new NingunConstructorValidoException();
	}

	private boolean puedoUsarElConstructor(Constructor<?> constructor, Object[] argumentos) {
		// map a la colección de argumentos, comparar con los del constructor
		Class<?>[] tiposDeParametro = constructor.getParameterTypes();
		for (int i = 0; i < tiposDeParametro.length; i++) {
			Class<?> tipoBindeado = argumentos[i].getClass();
			Class<?> tipoConstructor = tiposDeParametro[i];

			if (!tipoConstructor.isAssignableFrom(tipoBindeado))
				return false;
		}
		return true;
	}
}

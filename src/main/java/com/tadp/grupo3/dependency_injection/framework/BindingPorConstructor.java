package com.tadp.grupo3.dependency_injection.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.tadp.grupo3.dependency_injection.exceptions.NingunConstructorValidoException;
import com.tadp.grupo3.dependency_injection.exceptions.SeRompioTodoException;

//Binding que conoce la clase a instanciar y los objetos para el constructor
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
		Object[] argumentos = this.procesarObjetosPorId(framework);
		
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

	//Convierte todos los objetos por id en los objetos reales
	private Object[] procesarObjetosPorId(Inyectador framework) {
		Object[] argumentos = this.argumentos.toArray();
		
		//map
		for (int i=0; i < argumentos.length; i++)
			argumentos[i] = framework.procesarObjetoPorId(argumentos[i]);
		
		return argumentos;
	}

	//Elige el constructor que matchee con los argumentos bindeados
	//Si hay muchos, se queda con el primero, si no hay ninguno rompe
	private Constructor<?> elegirConstructor(Object[] argumentos) {
		Constructor<?>[] constructores = this.clase.getConstructors();

		for (Constructor<?> constructor : constructores) {
			if (this.puedoUsarElConstructor(constructor, argumentos))
				return constructor;
		}
		throw new NingunConstructorValidoException();
	}

	//Determina si los argumentos bindeados me permiten usar un constructor dado
	private boolean puedoUsarElConstructor(Constructor<?> constructor, Object[] argumentos) {
		// map a la colección de argumentos, comparar con los del constructor
		Class<?>[] tiposDeParametro = constructor.getParameterTypes();
		int argumentosEsperados = tiposDeParametro.length;
		
		if (argumentos.length != argumentosEsperados)
			return false;
		
		for (int i = 0; i < argumentosEsperados; i++) {
			Class<?> tipoBindeado = argumentos[i].getClass();
			Class<?> tipoConstructor = tiposDeParametro[i];

			if (!tipoConstructor.isAssignableFrom(tipoBindeado))
				return false;
		}
		return true;
	}
}

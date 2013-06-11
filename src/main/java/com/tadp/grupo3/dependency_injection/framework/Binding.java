package com.tadp.grupo3.dependency_injection.framework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.tadp.grupo3.dependency_injection.exceptions.NingunConstructorValidoException;
import com.tadp.grupo3.dependency_injection.exceptions.SeRompioTodoException;

public class Binding {
	private Class<?> clase;
	private List<Object> argumentos;
	
	public Binding(Class<?> clase) {
		this.clase = clase;
		this.argumentos = new ArrayList<Object>();
	}
	
	public void agregarArgumento(Object argumento) {
		this.argumentos.add(argumento);
	}
	
	public Object instanciar() {
		Constructor constructor = this.elegirConstructor();
		try {
			return constructor.newInstance(this.argumentos.toArray());
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

	private Constructor elegirConstructor() {
		Constructor<?>[] constructores = this.clase.getConstructors();
		
		for (Constructor constructor : constructores) {
			if (this.puedoUsarElConstructor(constructor))
				return constructor;
		}
		throw new NingunConstructorValidoException();
	}

	private boolean puedoUsarElConstructor(Constructor constructor) {
		//map a la colección de argumentos, comparar con los del constructor
		Class<?>[] tiposDeParametro = constructor.getParameterTypes();
		for (int i=0; i < tiposDeParametro.length; i++) {
			Class<?> tipoBindeado = this.argumentos.get(i).getClass();
			Class<?> tipoConstructor = tiposDeParametro[i];
			
			if (tipoBindeado != tipoConstructor)
				return false;
		}
		return true;
	}
}

package com.tadp.grupo3.dependency_injection.framework;

import com.tadp.grupo3.dependency_injection.exceptions.SeRompioTodoException;

public class Binding {
	private Class<?> clase;
	
	public Binding(Class<?> clase) {
		this.clase = clase;
	}

	public Object instanciar() {
		try {
			return this.clase.newInstance();
		} catch (InstantiationException e) {
			throw new SeRompioTodoException();
		} catch (IllegalAccessException e) {
			throw new SeRompioTodoException();
		}
	}
}

package com.tadp.grupo3.dependency_injection.framework;

import java.lang.reflect.Method;

//Representa un setter a ejecutar, con el valor a asignar
public class Setter {
	private Method method;
	private Object valor;
	
	public Setter(Method unMethod, Object unValor) {
		this.method = unMethod;
		this.valor = unValor;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}
}
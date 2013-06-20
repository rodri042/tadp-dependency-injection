package com.tadp.grupo3.dependency_injection.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.tadp.grupo3.dependency_injection.exceptions.SeRompioTodoException;

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

	//Invoca al setter para un objeto
	public void invocarPara(Object unObjeto, Inyectador framework) {
		Method unMethod = this.getMethod();
		try {
			unMethod.invoke(unObjeto, framework.procesarObjetoPorId(this.getValor()));
		} catch (IllegalAccessException e) {
			throw new SeRompioTodoException();
		} catch (IllegalArgumentException e) {
			throw new SeRompioTodoException();
		} catch (InvocationTargetException e) {
			throw new SeRompioTodoException();
		}
	}
}
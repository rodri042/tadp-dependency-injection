package com.tadp.grupo3.dependency_injection.framework;

import java.util.ArrayList;
import java.util.List;
import com.tadp.grupo3.dependency_injection.exceptions.NoHayConstructorVacioException;
import com.tadp.grupo3.dependency_injection.exceptions.SeRompioTodoException;

//Binding que conoce la clase a instanciar y los setters a ejecutar
public class BindingPorAccessor implements Binding {
	private Class<?> clase;
	private List<Setter> setters;

	public BindingPorAccessor(Class<?> unaClase) {
		this.clase = unaClase;
		this.setters = new ArrayList<Setter>();
	}

	public Object instanciar(Inyectador framework) {
		try {	
			Object unObjeto = clase.newInstance();
			
			for (Setter unSetter : setters)
				unSetter.invocarPara(unObjeto, framework);
			
			return unObjeto;
		} catch (InstantiationException e) {
			throw new NoHayConstructorVacioException();
		} catch (IllegalAccessException e) {
			throw new SeRompioTodoException();
		}
	}

	public void addSetter(Setter unSetter) {
		this.setters.add(unSetter);
	}

	public Class<?> getClase() {
		return clase;
	}

	public void setClase(Class<?> clase) {
		this.clase = clase;
	}
}

package com.tadp.grupo3.dependency_injection.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
			
			for (Setter unSetter : setters) {
				
				Method unMethod = unSetter.getMethod();
				
				unMethod.invoke(unObjeto, unSetter.getValor());
				 
			}
			
			return unObjeto;
			
		}catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
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

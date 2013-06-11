package com.tadp.grupo3.dependency_injection.framework;

public class BindingPorAccessor implements Binding {
	
	private Class<?> clase;
	
	public BindingPorAccessor(Class<?> unaClase){
		
		this.clase = unaClase;
		
	}
	
	public Object instanciar(Inyectador framework) {
		
		try {
			return clase.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
}

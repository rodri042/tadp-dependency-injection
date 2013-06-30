package com.tadp.grupo3.dependency_injection.dsl

import com.tadp.grupo3.dependency_injection.framework.*

public class Dsl {
	static def required() { }
	static public def es
	
	static {
		Inyectador.metaClass.dijeramosQue = { bloque ->
			new InyectadorConfigurator(delegate).with bloque
		}
		
		BindingBuilder.metaClass.con = { bloque ->
			delegate.with bloque
		}
		
		BindingBuilder.metaClass.un = { nombre ->
			new IdAtributo(nombre, delegate)
		}
	}	
}

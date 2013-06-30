package com.tadp.grupo3.dependency_injection.dsl

import com.tadp.grupo3.dependency_injection.framework.*

public class Dsl {
	static def required() { }
	static public def es
	
	static {
		Inyectador.metaClass.dijeramosQue = { bloque ->
			new DefinicionBinding(delegate).with bloque
		}
		
		BindingBuilder.mixin(BindingBuilderDsl)
	}	
}

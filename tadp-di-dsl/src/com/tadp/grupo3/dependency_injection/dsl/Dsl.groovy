package com.tadp.grupo3.dependency_injection.dsl

public class Dsl {
	static def required() { }
	
	static {
		Inyectador.metaClass.dijeramosQue = { -> 
			new InyectadorConfigurator()
		}
	}	
}

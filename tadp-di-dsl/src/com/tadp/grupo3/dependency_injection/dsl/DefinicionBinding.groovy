package com.tadp.grupo3.dependency_injection.dsl;
import com.tadp.grupo3.dependency_injection.framework.*


public class DefinicionBinding {
	def framework
	
	DefinicionBinding(framework) {
		this.framework = framework;
	}
	
	def methodMissing(String idBinding, es) {
		new IdBinding(idBinding, framework)
	}
}

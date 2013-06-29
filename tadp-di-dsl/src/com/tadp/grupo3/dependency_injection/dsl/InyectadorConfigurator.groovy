package com.tadp.grupo3.dependency_injection.dsl;
import com.tadp.grupo3.dependency_injection.framework.*

public class InyectadorConfigurator {
	def framework
	
	InyectadorConfigurator(framework) {
		this.framework = framework;
	}
	
	def propertyMissing(String idBinding) {
		new IdBinding(idBinding, framework)
	}
}

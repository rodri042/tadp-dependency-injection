package com.tadp.grupo3.dependency_injection.dsl;

public class InyectadorConfigurator {
	Inyectador framework;
	
	InyectadorConfigurator(Inyectador framework) {
		this.framework = framework;
	}
	
	def propertyMissing(String idBinding) {
		3
		//new IdBinding(idBinding)
	}
}

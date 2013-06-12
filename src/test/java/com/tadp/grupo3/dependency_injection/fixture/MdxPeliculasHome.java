package com.tadp.grupo3.dependency_injection.fixture;

public class MdxPeliculasHome extends PeliculasHome {
	Logger logger;
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
}

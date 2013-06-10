package com.tadp.grupo3.dependency_injection.fixture;

public class CineController {
	public PeliculasHome peliculasHome;
	
	public CineController() { }
	
	public PeliculasHome getPeliculasHome() {
		return peliculasHome;
	}

	public void setPeliculasHome(PeliculasHome peliculasHome) {
		this.peliculasHome = peliculasHome;
	}
}

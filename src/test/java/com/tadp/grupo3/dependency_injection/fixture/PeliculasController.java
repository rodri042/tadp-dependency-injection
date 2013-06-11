package com.tadp.grupo3.dependency_injection.fixture;

import java.util.List;

public class PeliculasController {

	private PeliculasHome homePeliculas;
	private List<PeliculasHome> homesPeliculas;
	
	public String cadena;
	
	public PeliculasController(PeliculasHome unHome){
		this.setHomePeliculas(unHome);
	}
	
	public PeliculasController(List<PeliculasHome> unosHomes){
	 	this.setHomesPeliculas(unosHomes);
	}

	public PeliculasHome getHomePeliculas() {
		return homePeliculas;
	}

	public void setHomePeliculas(PeliculasHome homePeliculas) {
		this.homePeliculas = homePeliculas;
	}

	public List<PeliculasHome> getHomesPeliculas() {
		return homesPeliculas;
	}
	
	public void setHomesPeliculas(List<PeliculasHome> homesPeliculas) {
		this.homesPeliculas = homesPeliculas;
	}
}

package com.tadp.grupo3.dependency_injection.framework;

//Representa un binding a una clase
public interface Binding {
	//Instancia el objeto que bindea
	abstract Object instanciar(Inyectador framework);
}

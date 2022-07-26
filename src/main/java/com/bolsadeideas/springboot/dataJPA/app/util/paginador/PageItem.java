package com.bolsadeideas.springboot.dataJPA.app.util.paginador;

public class PageItem {

	private int numero;
	private boolean actual;
	
	
	
	
	
	//CONSTRUCTORES
	public PageItem(int numero, boolean actual) {
		this.numero = numero;
		this.actual = actual;
	} 
	
	
	//GETTERS Y SETTERS
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public boolean isActual() {
		return actual;
	}
	public void setActual(boolean actual) {
		this.actual = actual;
	}

	
	
	
}

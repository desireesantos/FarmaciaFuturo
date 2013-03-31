package br.com.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;

public class TrocarTema implements Serializable{

	private static final long serialVersionUID = 5157498947544036191L;
	
	private String tema;
	private String nomeConectividade;
	

	public TrocarTema() {

		this.nomeConectividade = "Controle Remoto";
		this.tema = "humanity";
		
	}
	
	
	 @PostConstruct  
	    public void init() {  
		 this.nomeConectividade = "Controle Remoto";
		 this.tema = "humanity";
		
	     
	    }  

	public String getTema() {

		return tema;
	}

	public void setTema(String tema) {

		this.tema = tema;
	}

	public String getNomeConectividade() {

		return nomeConectividade;
	}

	public void setNomeConectividade(String nomeConectividade) {

		this.nomeConectividade = nomeConectividade;
	}


}

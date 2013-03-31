package br.com.bean;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


import br.com.bean.Participante;


/**
 * Entity implementation class for Entity: Administrador
 *
 */
@Entity
@DiscriminatorValue("A")
public class Administrador extends Participante implements Serializable{
	private static final long serialVersionUID = 1L;

	
	private String email;
	private String senha;
	private Participante participante;
	

	public Administrador() 
	{
		
	}


	
	public String getSenha() {
		return senha;
	}


	public Participante getParticipante() {
		return participante;
	}

	
	public String getEmail() {
		System.out.println("NOME:"+ this.email);
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}



	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	
	
	public String login(){
		
		if (participante.getNome().equalsIgnoreCase("tester") && senha.equalsIgnoreCase("tester")){
			return "success";
		} else {
			return "failure";
		}
		
	}



		
}
	
	

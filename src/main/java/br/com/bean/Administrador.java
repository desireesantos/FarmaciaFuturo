package br.com.bean;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;



/**
 * Entity implementation class for Entity: Administrador
 *
 */
@Entity
@DiscriminatorValue("A")
public class Administrador  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String senha;
	
		

	public Administrador() 
	{
		
		
	}


	
	public String getSenha() {
		return senha;
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



	

		
}
	
	

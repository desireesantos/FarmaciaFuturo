package br.com.bean;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;


/**
 * Entity implementation class for Entity: Participante
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoParticipante",discriminatorType=DiscriminatorType.STRING)
public class Participante implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id()
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDparticipante")
	@SequenceGenerator(sequenceName="id_participante", name="IDparticipante", allocationSize=0)
	private int id;
	
	private String nome;
	
	public Participante() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

package br.com.bean;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
/*import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
*/

/**
 * Entity implementation class for Entity: Usuario
 * 
 */

@Entity
@DiscriminatorValue("U")
public class Usuario extends Participante implements Serializable {
	private static final long serialVersionUID = 1L;

	private String resposta;
	private Participante participante = new Participante();


	private int quiz;
	

	public Usuario() {
		Usuario.this.setParticipante(new Participante());
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public int getQuiz() {
		return quiz;
	}

	public void setQuiz(int quiz) {
		this.quiz = quiz;
	}

	

}

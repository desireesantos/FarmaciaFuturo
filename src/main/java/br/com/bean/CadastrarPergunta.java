package br.com.bean;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.faces.component.UIColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "pergunta")
@SessionScoped
@SequenceGenerator(name = "seq_pergunta", sequenceName="seq_pergunta", allocationSize=1)
public class CadastrarPergunta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_pergunta")
	private int id;

	private String value;
	private String respostaCorreta;
	private String resposta1;
	private String resposta2;
	private String resposta3;
	private String resposta4;
	private String imagem;
	
	
	@Transient
	private String autoComplete;

	@Transient
	private UIColumn coluna;


	
	public CadastrarPergunta() {

	}
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getResposta1() {
		return resposta1;
	}

	public void setResposta1(String resposta1) {

		this.resposta1 = resposta1;
	}

	public String getResposta2() {

		return resposta2;
	}

	public void setResposta2(String resposta2) {

		this.resposta2 = resposta2;
	}

	public String getResposta3() {
		return resposta3;
	}

	public void setResposta3(String resposta3) {

		this.resposta3 = resposta3;
	}

	public String getResposta4() {
		return resposta4;
	}

	public void setResposta4(String resposta4) {

		this.resposta4 = resposta4;
	}

	public String getRespostaCorreta() {
		return respostaCorreta;
	}

	public void setRespostaCorreta(String respostaCorreta) {
		this.respostaCorreta = respostaCorreta;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
		System.out.println("IMPRIMIR NOME:"+ imagem);
	}

	
	
	public UIColumn getColuna() {
		return coluna;
	}

	public void setColuna(UIColumn coluna) {
		this.coluna = coluna;
	}

	public String getAutoComplete() {
		return autoComplete;
	}

	public void setAutoComplete(String autoComplete) {

		this.autoComplete = autoComplete;

	}

	public String getImagem() {
		return "/Users/desireesantos/Desktop/PROJETOFINAL/arquivos/"+imagem;
	}


	 
}

package br.com.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.bean.CadastrarPergunta;
import br.com.bean.QuizRealizada;
import br.com.bean.Usuario;
import br.com.infraestrutura.QuizRealizadaDAO;

@SessionScoped
public class QuizRealizadaMB implements Serializable {

	private static final long serialVersionUID = 6565426993617736286L;

	private QuizRealizada quizRealizada = new QuizRealizada();

	private CadastrarPergunta cadastrarPergunta;

	private List<QuizRealizada> listarQuizRealizadas;
	
	private List<Usuario> listarUsuarioRealizadas;
	
	private String respostaCorreta;
	
	private String btnIniciar;

	public QuizRealizadaMB() {
		System.out.println("Iniciou construtor QuizMB");

		btnIniciar = "Valendo";

		QuizRealizadaMB.this.setQuizRealizada(new QuizRealizada());
		

	}


	public String getBtnIniciar() {
		return btnIniciar;
	}

	public void setBtnIniciar(String btnIniciar) {
		this.btnIniciar = btnIniciar;
	}

	public QuizRealizada getQuizRealizada() {
		return quizRealizada;
	}

	public void setQuizRealizada(QuizRealizada quizRealizada) {
		
		this.quizRealizada = quizRealizada;
		System.out.println("QUIZREALIZADA: "+ quizRealizada.getId());
	}

	@SuppressWarnings("unused")
	private CadastrarPergunta chamarPergunta() {

		String ref = "cadastrarPergunta";
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext elContext = facesContext.getELContext();
		ExpressionFactory factory = facesContext.getApplication()
				.getExpressionFactory();
		CadastrarPergunta cadastrar = (CadastrarPergunta) factory
				.createValueExpression(elContext, "#{" + ref + "}",
						Object.class).getValue(elContext);


		return cadastrar;
	}

	/**
	 * Grava no banco de dados Enquete
	 */
	public void salvar() {
		Boolean estado = false;
		System.out.println("Entrou no salvar enquete");

		QuizRealizadaDAO dao = new QuizRealizadaDAO();
		estado = dao.salvar(this.quizRealizada);
		if (estado) {
			System.out.println("Enquete gravada com sucesso");
		} else {
			System.out.println("Erro ao gravar a enquete");
		}
	}

	public void ativarEnquete() {

		if (btnIniciar.equals("Terminar")) {
			this.btnIniciar = "Valendo";
		}
		

	}

	public void iniciarEnquete() {
		this.btnIniciar = "Terminar";
		salvar();

	}

	public CadastrarPergunta getCadastrarPergunta() {
		return cadastrarPergunta;
	}

	public void setCadastrarPergunta(CadastrarPergunta cadastrarPergunta) {
		this.cadastrarPergunta = cadastrarPergunta;
	}


	public List<QuizRealizada> getListarQuizRealizadas() throws ParseException {
		QuizRealizadaDAO dao = new QuizRealizadaDAO();
    	
		return listarQuizRealizadas = dao.listarQuizRealizadas();
	}


	public void setListarQuizRealizadas(List<QuizRealizada> listarQuizRealizadas) {
		this.listarQuizRealizadas = listarQuizRealizadas;
	}
	
 
	


	public List<Usuario> getListarUsuarioRealizadas() {
		QuizRealizadaDAO dao = new QuizRealizadaDAO();
		List<Usuario> users = new ArrayList<Usuario>();
		
	
		
		for (Usuario usuario : dao.listarUsuarioRealizadas()) {
		
			if (usuario.getQuiz() == quizRealizada.getId()) {
				users.add(usuario);
			}
		}
		
		return users;
	}


	public void setListarUsuarioRealizadas(List<Usuario> listarUsuarioRealizadas) {
		this.listarUsuarioRealizadas = listarUsuarioRealizadas;
	}


	public String getRespostaCorreta() {
		
		
		return quizRealizada.getCadastrarPergunta().getRespostaCorreta() ;
	}


	public void setRespostaCorreta(String respostaCorreta) {
		this.respostaCorreta = quizRealizada.getCadastrarPergunta().getRespostaCorreta();;
	}
	


}
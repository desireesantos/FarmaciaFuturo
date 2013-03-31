package br.com.service;

import java.io.Serializable;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.bean.CadastrarPergunta;
import br.com.bean.QuizRealizada;
import br.com.infraestrutura.QuizRealizadaDAO;

@SessionScoped
public class QuizRealizadaMB implements Serializable {

	private static final long serialVersionUID = 6565426993617736286L;

	private QuizRealizada quizRealizada = new QuizRealizada();

	private CadastrarPergunta cadastrarPergunta;
	
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

		System.out.println("Id pergunta selecionada:" + cadastrar.getId());

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
		System.out.println("Enquete:" + btnIniciar);

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
	
//	private void obterNomeAdmin() {
//		System.out.println("+++++Entrou em ObterNome++++");
//		AdministradorDAO dao = new AdministradorDAO();
//		AdminLogado adminlogado = new AdminLogado();
//		adminlogado = dao.findLogadoByEmail();
//		System.out.println(adminlogado.getNome());
//		this.quizRealizada.setNomeAdministrador(adminlogado.getNome());
//	}


}
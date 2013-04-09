package br.com.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.ExpressionFactory;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;





@Entity
@Table(name ="quizrealizada")
@SequenceGenerator(name ="quiz_id", sequenceName="quiz_id" ,allocationSize = 1, initialValue=0)
public class QuizRealizada {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator="quiz_id")
	private int id_quiz;
	
	@ManagedProperty(value="br.com.bean.CadastrarPergunta" ,name="cadastrarPergunta" )
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="pergunta_id", referencedColumnName="id", updatable=true,insertable=true)	
	private CadastrarPergunta cadastrarPergunta;
	
	private String tipoConectividade;

	private Date inicioDataHora;

	private String nomeAdministrador;

	private int resultadoFinal;
	

	public QuizRealizada()  {
		
		QuizRealizada.this.setCadastrarPergunta(new CadastrarPergunta());
		
		this.tipoConectividade = ((TrocarTema) obterMangeBean("trocarTema"))
				.getNomeConectividade();
		
		System.out.println("TipoConectividade: "+ this.tipoConectividade);
		
		cadastrarPergunta =  obterCadastrarManageBean();
		System.out.println("AGORA..."+ cadastrarPergunta.getValue());
		obterDataHora();
		
		
	}
	
	
	private void obterDataHora()  {
		Date hoje = new Date();
		String formato = "dd/MM/yyyy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
		System.out.println("A data e hora formatada: "
				+ simpleDateFormat.format(hoje));

		
			try {
				this.inicioDataHora =  simpleDateFormat.parse(simpleDateFormat.format(hoje));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		
	}

	
	private CadastrarPergunta obterCadastrarManageBean(){
		
		String ref = "cadastrarPergunta";
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ELContext elContext = facesContext.getELContext();
		ExpressionFactory factory = facesContext.getApplication().getExpressionFactory();
		CadastrarPergunta cadastrar = (CadastrarPergunta) factory.createValueExpression(elContext, "#{" + ref + "}", Object.class).getValue(elContext);
			System.out.println("Id pergunta selecionada:" + cadastrar.getId());	

		 return cadastrar;
			}
	
	
	private Object obterMangeBean(String manageBean) {
 		FacesContext context = FacesContext.getCurrentInstance();
		ELResolver resolver = context.getApplication().getELResolver();
		return resolver.getValue(context.getELContext(), null, manageBean);

	}

	public CadastrarPergunta getCadastrarPergunta() {
		return cadastrarPergunta;
	}


	public String getTipoConectividade() {
		this.tipoConectividade = ((TrocarTema) obterMangeBean("trocarTema"))
				.getNomeConectividade();
		System.out.println("TipoConectividade: "+ this.tipoConectividade);
		
		return tipoConectividade;
	}

	public Date getInicioDataHora() {
		//obterDataHora();	
		return inicioDataHora;
	}



	public int getResultadoFinal() {
		return resultadoFinal;
	}

	public void setCadastrarPergunta(CadastrarPergunta cadastrarPergunta) {
		
		this.cadastrarPergunta = cadastrarPergunta;
	}


	public void setTipoConectividade(String tipoConectividade) {
		this.tipoConectividade = ((TrocarTema) obterMangeBean("trocarTema"))
				.getNomeConectividade();
		System.out.println("TipoConectividade: "+ this.tipoConectividade);
		
		this.tipoConectividade = tipoConectividade;
	}

	public void setInicioDataHora(Date inicioDataHora) {
		obterDataHora();
		this.inicioDataHora = inicioDataHora;
	}


	public void setResultadoFinal(int resultadoFinal) {
		this.resultadoFinal = resultadoFinal;
	}

	public int getId() {
		return id_quiz;
	}

	public void setId(int id) {
		this.id_quiz = id;
	}



	public String getNomeAdministrador() {
		return nomeAdministrador;
	}



	public void setNomeAdministrador(String nomeAdministrador) {
		this.nomeAdministrador = nomeAdministrador;
	}



	


}

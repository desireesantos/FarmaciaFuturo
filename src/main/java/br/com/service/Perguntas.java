package br.com.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

import br.com.infraestrutura.AdministradorDAO;
import br.com.infraestrutura.PerguntaDAO;
import br.com.infraestrutura.QuizRealizadaDAO;
import br.com.bean.AdminLogado;
import br.com.bean.CadastrarPergunta;
import br.com.bean.ChartBean;
import br.com.bean.QuizRealizada;

public class Perguntas implements Serializable {

	private static final long serialVersionUID = 6565426993617736286L;

	private CadastrarPergunta cadastrarPergunta = new CadastrarPergunta();
	private DataModel listarCadastrarPerguntas;
	//private DataModel pergunta;
	private FacesContext facesContext;
	private final static String[] opcoes;
	private List<CadastrarPergunta> perguntasRelatorio;
	private String btnIniciar;


	static {
		opcoes = new String[4];
		opcoes[0] = "1";
		opcoes[1] = "2";
		opcoes[2] = "3";
		opcoes[3] = "4";
	}

	public Perguntas() {
		
		this.btnIniciar = "Iniciar";	
		
		
		Perguntas.this.setCadastrarPergunta(new CadastrarPergunta());
	

	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}



	public String[] getOpcoes() {
		return opcoes;
	}

	public CadastrarPergunta getCadastrarPergunta() {
		return cadastrarPergunta;
	}

	public void setCadastrarPergunta(CadastrarPergunta cadastrarPergunta) {
		this.cadastrarPergunta = cadastrarPergunta;
	}

	
	public DataModel getListarCadastrarPerguntas() {

		PerguntaDAO dao = new PerguntaDAO();

		if (listarCadastrarPerguntas == null) {
			List<CadastrarPergunta> lista = dao.listarPerguntas();
			listarCadastrarPerguntas = new ListDataModel(lista);

			// update();

		}

		return listarCadastrarPerguntas;
	}

	/**
	 * Grava no banco de dados pergunta
	 * @throws IOException 
	 */
	public void salvar() throws IOException {
		Boolean estado = false;
		System.out.println("Entrou para salvar");
		System.out.println(".:::NOME IMAGEM:" + cadastrarPergunta.getImagem()
				+ "::..");

		PerguntaDAO dao = new PerguntaDAO();
		estado = dao.salvar(cadastrarPergunta);
		limpaTela();
		atualizarDadosDataTable();
		if (estado) {
			enviarMensagem("Cadastrada com sucesso");

		} else {
			enviarMensagem("Erro ao cadastrar");
		}
		
		
	}

	/**
	 * Obter lista de pergunta para autoComplete da tela buscar
	 * 
	 * @param query
	 * @return List<CadastrarPergunta>
	 */
	public List<String> prebusca(String query) {
		List<String> r = new ArrayList<String>();
		PerguntaDAO dao = new PerguntaDAO();

		for (int i = 0; i < dao.listaTodos(query).size(); i++) {
			r.add(dao.listaTodos(query).get(i));

		}

		return r;
	}

	/**
	 * Atualiza no banco de dados pergunta
	 */
	public void update() {

		PerguntaDAO dao = new PerguntaDAO();

		CadastrarPergunta atualizaPergunta = (CadastrarPergunta) (listarCadastrarPerguntas
				.getRowData());
		dao.update(atualizaPergunta);

	}

	/**
	 * Deleta do banco a pergunta que está no objeto cadastrarPergunta, que foi
	 * setado na view.
	 */
	public void excluir() {

		PerguntaDAO dao = new PerguntaDAO();

		CadastrarPergunta deletaPergunta = (CadastrarPergunta) (listarCadastrarPerguntas
				.getRowData());
		List<CadastrarPergunta> temp = dao.excluir(deletaPergunta);
		listarCadastrarPerguntas = new ListDataModel(temp);
		System.out.println("Objeto EXCLUIDO");

		if (listarCadastrarPerguntas != null) {
			enviarMensagem("Removida com sucesso ");

		} else {
			enviarMensagem("Erro ao remover, tente novamente.");
		}

	}

	/**
	 * @code Persistir no banco dados atualizados da tela Buscar questionário
	 * @param event
	 */
	public void onEditRow(RowEditEvent event) {

		CadastrarPergunta temp = (CadastrarPergunta) event.getObject();

		PerguntaDAO dao = new PerguntaDAO();
		dao.update(temp);

	}

	/**
	 * @code Exibir coluna resposta correta quando seleciona coluna
	 * @param event
	 */
	public void onRenderCorreta() {

		// render = true;

	}

	/**
	 * Limpar tela
	 */
	public void limpaTela() {

		cadastrarPergunta = new CadastrarPergunta();
	}

	/**
	 * Exibir mensagem na tela
	 * 
	 * @param msg
	 */
	public void enviarMensagem(String msg) {

		facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(msg));

	}

	/*
	 * public void arquivoUpload(FileUploadEvent event) { final String path =
	 * "/Users/desireesantos/Desktop/PROJETOFINAL/CliqueON/WebContent/WEB-INF/imgUpload"
	 * ; try { System.out.println("ENTROU AQUI !"); ExternalContext
	 * externalContext = FacesContext.getCurrentInstance()
	 * .getExternalContext();
	 * 
	 * FacesContext aFacesContext = FacesContext.getCurrentInstance();
	 * ServletContext context = (ServletContext) aFacesContext
	 * .getExternalContext().getContext();
	 * 
	 * File file = new File(path); file.mkdirs();
	 * 
	 * byte[] conteudo = event.getFile().getContents();
	 * 
	 * String caminho = path + event.getFile().getFileName(); FileOutputStream
	 * fos = new FileOutputStream(caminho); fos.write(conteudo); fos.close();
	 * 
	 * this.cadastrarPergunta.setImagem(event.getFile().getFileName());
	 * System.out.println("Imagem" + cadastrarPergunta.getImagem());
	 * 
	 * } catch (Exception ex) { System.out.println("Erro ao Upload Imagem"); } }
	 */

	/**
	 * Grava no banco de dados pergunta
	 */
	public void atualizarDadosDataTable() {

		// CadastrarPergunta item = (CadastrarPergunta) getData().getRowData();
		Perguntas.this.setCadastrarPergunta(new CadastrarPergunta());
		PerguntaDAO dao = new PerguntaDAO();

		List<CadastrarPergunta> temp = dao.listarPerguntas();
		listarCadastrarPerguntas = new ListDataModel(temp);

	}

	public List<CadastrarPergunta> getPerguntasRelatorio() {

		PerguntaDAO dao = new PerguntaDAO();

		perguntasRelatorio = dao.listarPerguntas();

		return perguntasRelatorio;
	}

	public void setPerguntasRelatorio(List<CadastrarPergunta> perguntas) {
		this.perguntasRelatorio = perguntas;
	}

	public void preProcessPDF(Object document) throws IOException,
			BadElementException, DocumentException {
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);

		ServletContext servletContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		String logo = servletContext.getRealPath("") + File.separator + "img"
				+ File.separator + "img_headerSIPA.png";

		pdf.add(Image.getInstance(logo));
	}

	public void upload(FileUploadEvent event) {
		FacesMessage msg = new FacesMessage("Success! ", event.getFile()
				.getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		File file = new File("excel.png");
		System.out.println("Caminho absoluto: " + file.getAbsolutePath());
		// Do what you want with the file
		try {
			copyFile(event.getFile().getFileName(), event.getFile()
					.getInputstream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void copyFile(String fileName, InputStream in) {
		try {

			String destination = "//Users//desireesantos//Desktop//eclipse//arquivo";
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(destination
					+ fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

			System.out.println("New file created!" + destination);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void handleFileUpload(FileUploadEvent event) throws IOException {

		byte[] foto = event.getFile().getContents();
		String destination = "//Users//desireesantos//Desktop//PROJETOFINAL//arquivos";

		File file = new File(destination);

		String filePath = file + "/" + event.getFile().getFileName();
		System.out.println("Foto:" + foto.toString());
		System.out.println("filePath:" + filePath);

		criaArquivo(foto, filePath);

		// Obter nome da imagem para gravar no banco
		cadastrarPergunta.setImagem(event.getFile().getFileName());
		FacesMessage msg = new FacesMessage("Imagem Enviada:", event.getFile()
				.getFileName());
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	private void criaArquivo(byte[] foto, String file) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(file);
		outputStream.write(foto);
		outputStream.close();

	}

	public String imagemCaminhoAbsoluto() {

		String destino = "//Users//desireesantos//Desktop//PROJETOFINAL//arquivos";
		return destino + cadastrarPergunta.getImagem();
	}

	public void prepararExibirPergunta() {
		cadastrarPergunta = (CadastrarPergunta) listarCadastrarPerguntas
				.getRowData();
	}

	public void ativarEnquete() {

		if (btnIniciar.equals("Terminar")) {
			this.btnIniciar = "Iniciar";
		}
		System.out.println("Enquete:" + btnIniciar);

	}

	public void iniciarEnquete() throws IOException {
		
		if (this.btnIniciar == "Terminar") {

			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/ClickerBrOn/page/grafico.xhtml");
		
		}
		
		this.btnIniciar = "Terminar";
		
		salvarEnquete();
		

	}


	private void salvarEnquete() {
		
		QuizRealizada quizRealizada = new QuizRealizada();
		quizRealizada.setCadastrarPergunta(cadastrarPergunta);
		
		Boolean estado = false;
		System.out.println("Entrou no salvar enquete");

		QuizRealizadaDAO dao = new QuizRealizadaDAO();
		
		AdministradorDAO adminDao = new AdministradorDAO();
		quizRealizada.setNomeAdministrador(adminDao.findLogadoByEmail());
		
		
		estado = dao.salvar(quizRealizada);
		if (estado) {
			System.out.println("Enquete gravada com sucesso");
		} else {
			System.out.println("Erro ao gravar a enquete");
		}		
		
	}
	
/*	public void dataHoraFim() {
		System.out.println("-----Entrou no gravar DataHora");
		QuizRealizada quizRealizada = new QuizRealizada();
		quizRealizada.setCadastrarPergunta(cadastrarPergunta);
		Date dataHoraFimEnquete = quizRealizada.getFimDataHora();
		
		QuizRealizadaDAO dao = new QuizRealizadaDAO();
		dao.gravarHora(dataHoraFimEnquete);
		
	}*/
	

	public String getBtnIniciar() {
		
		return btnIniciar;
	}

	public void setBtnIniciar(String btnIniciar) {
		this.btnIniciar = btnIniciar;
	}


	






}
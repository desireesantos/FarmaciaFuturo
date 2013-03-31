package br.com.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.bean.AdminLogado;
import br.com.bean.Administrador;
import br.com.bean.Participante;
import br.com.infraestrutura.AdministradorDAO;

public class AdministradorMBean extends Participante implements Serializable {

	private static final long serialVersionUID = 1L;

	private Administrador administrador = new Administrador();
	private FacesContext facesContext;
	
	
	public AdministradorMBean() {

		AdministradorMBean.this.setAdministrador(new Administrador());

	}

	public Administrador getAdministrador() {
		System.out.println("Nome user:" + administrador.getNome());
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;

	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;

	}

	public List<Administrador> getAdministradors() {

		AdministradorDAO dao = new AdministradorDAO();
		List<Administrador> lista = new ArrayList<Administrador>();

		lista = dao.listarAdministradors();
		return lista;
	}

	/**
	 * Grava no banco de dados administrador
	 * 
	 * @throws IOException
	 */
	public void salvar() throws IOException {
		Boolean estado = false;

		System.out.println("Entrou para salvar");

		AdministradorDAO dao = new AdministradorDAO();
		estado = dao.salvar(administrador);

		if (estado) {
			enviarMensagem("Enviado com sucesso");
			limpaTela();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/ClickerBrOn/page/apresentacao.xhtml");

		} else {
			enviarMensagem("Erro ao Votar");

		}

	}

	/**
	 * Limpar tela
	 */
	public void limpaTela() {

		administrador = new Administrador();
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

	/**
	 * Acessar o sistema
	 * 
	 * @throws IOException
	 */
	public String login() throws IOException {
		String status = null;
		System.out.println("Entrou no login");

		AdministradorDAO dao = new AdministradorDAO();
		Administrador admin = new Administrador();
		admin = dao.findByEmail(administrador.getEmail());
		System.out.println("Email do Banco: " + admin.getEmail());
		
	
		if (admin.getEmail().equals(administrador.getEmail())
				&& admin.getSenha().equals(administrador.getSenha())) {
			status = "sucesso";
			
			AdminLogado adminLogado = new AdminLogado();
			Administrador ad = new Administrador(); 
			ad = dao.findByEmail(administrador.getEmail()); 
			adminLogado.setNome(ad.getNome());
			adminLogado.setEmail(ad.getEmail());
			System.out.println("----adminLogado: "+ adminLogado.getNome());
			dao.salvarLogado(adminLogado);
			
			System.out.println(status);
			limpaTela();
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/ClickerBrOn/page/apresentacao.xhtml");
		} else {
			enviarMensagem("Tente novamente!");
			status = "fracasso";
			System.out.println(status);
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("login.xhtml");
		}
		return status;
	}

	public void  logout() throws IOException {
		
		FacesContext fc = FacesContext.getCurrentInstance();   
		HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);   
		session.invalidate();  
		
	    FacesContext.getCurrentInstance().getExternalContext()
		.redirect("/ClickerBrOn/page/login.xhtml");
	}


	
	
	}



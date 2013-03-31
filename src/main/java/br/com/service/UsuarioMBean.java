package br.com.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.bean.AdminLogado;
import br.com.bean.Administrador;
import br.com.bean.Usuario;
import br.com.infraestrutura.UsuarioDAO;

public class UsuarioMBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	private FacesContext facesContext;

	public UsuarioMBean() {

		UsuarioMBean.this.setUsuario(new Usuario());

	}

	public Usuario getUsuario() {
		System.out.println("Nome user:" + usuario.getNome());
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;

	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;

	}

	public List<Usuario> getUsuarios() {

		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> lista = new ArrayList<Usuario>();

		lista = dao.listarUsuarios();
		return lista;
	}

	/**
	 * Grava no banco de dados usuario
	 * @throws IOException 
	 */
	public void salvar() throws IOException {
		Boolean estado = false;
		
		System.out.println("Entrou para salvar");

		UsuarioDAO dao = new UsuarioDAO();		
		usuario.setQuiz( dao.findQuizID()); 
		
		estado = dao.salvar(usuario);
		limpaTela();
		if (estado) {
			enviarMensagem("Enviado com sucesso");
			
		} else {
			enviarMensagem("Erro ao Votar");

		}

	

	}

	/**
	 * Limpar tela
	 */
	public void limpaTela() {

		usuario = new Usuario();
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

}

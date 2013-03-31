package br.com.service;

import java.util.List;

import br.com.bean.Usuario;


public interface InterfaceUsuario {
	
	List<Usuario> listarUsuarios();

	void update(Usuario x);

	boolean salvar(Usuario usuario);

	List<Usuario> excluir(Usuario user);

}
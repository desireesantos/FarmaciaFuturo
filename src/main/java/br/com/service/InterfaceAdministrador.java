package br.com.service;

import java.util.List;

import br.com.bean.Administrador;


public interface InterfaceAdministrador {
	
	List<Administrador> listarAdministradors();

	void update(Administrador x);

	boolean salvar(Administrador Administrador);

	List<Administrador> excluir(Administrador user);

}
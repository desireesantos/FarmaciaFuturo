package br.com.infraestrutura;

import java.util.List;

import br.com.bean.CadastrarPergunta;


public interface InterfacePerguntaDAO {
	
	public Boolean salvar(CadastrarPergunta cadastrarPergunta);
	
	public List<String> listaTodos(String query); 


}

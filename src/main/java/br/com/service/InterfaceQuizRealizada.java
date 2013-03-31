package br.com.service;

import java.util.List;

import br.com.bean.QuizRealizada;


public interface InterfaceQuizRealizada {
	
	List<QuizRealizada> listarQuizRealizadas();

	void update(QuizRealizada x);

	public Boolean salvar(QuizRealizada	 quizRealizada);
	
	List<QuizRealizada> excluir(QuizRealizada quiz);

}
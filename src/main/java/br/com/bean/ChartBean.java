package br.com.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;

import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.infraestrutura.QuizRealizadaDAO;
import br.com.infraestrutura.UsuarioDAO;

public class ChartBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private PieChartModel livePieModel;
	private String chamarDialog= "";
	private int quantidadeAcerto = 0;
	private int quantidadeErro = 0;

	
	public ChartBean() {
		createLivePieModel();
	}

	

	public PieChartModel getLivePieModel() {
		
		atualizarDadosGrafico();
		livePieModel.getData().put("Acerto", quantidadeAcerto);
		livePieModel.getData().put("Erro", quantidadeErro);

		return livePieModel;
	}

	public void createLivePieModel() {
		livePieModel = new PieChartModel();

		livePieModel.set("Acerto", 0);  
		livePieModel.set("Erro", 0);  
		
	}
	
	

	public void atualizarDadosGrafico() {
		
		
		
		  QuizRealizadaDAO dao= new QuizRealizadaDAO(); 
		  Integer id_pergunta = dao.findIDPerguntaAtual(); 
		  String respostaCerta = dao.findRespostaCorretaQuizAtual(id_pergunta);
		  
		  System.out.println("VALORES CHART_id_Pergunta:"+ id_pergunta);
		  System.out.println("VALORES Resposta_certa:"+ respostaCerta);

		UsuarioDAO userDao = new UsuarioDAO();
		Integer id_quiz = dao.findIDQuizAtual();
		int pontos[] =  userDao.listarParticipantesQuiz(id_quiz, respostaCerta);
		  
		  quantidadeAcerto = pontos[0]; 
		  quantidadeErro = pontos[1]; 
		 

	}



	



	

	

}

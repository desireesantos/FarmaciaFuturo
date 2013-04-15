package br.com.infraestrutura;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import br.com.bean.QuizRealizada;
import br.com.bean.Usuario;
import br.com.service.InterfaceQuizRealizada;
import br.com.util.HibernateUtil;

public class QuizRealizadaDAO implements InterfaceQuizRealizada {

	// MŽtodo respons‡vel por salvar os dados no BD

	public Boolean salvar(QuizRealizada quizRealizada) {

		System.out.println("Entrou no SALVAR ENQUETE DAO !");
		Configuration configuration = new Configuration();
		configuration.configure();

		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();

		System.out.println("Nome do ADMIN:"
				+ quizRealizada.getCadastrarPergunta().getValue());
		System.out.println("Session Factory" + session.getSessionFactory());

		Transaction tx = session.beginTransaction();
		session.save(quizRealizada);

		Integer x = 0;
		x = findIDPerguntaAtual() - 1;
		session.createSQLQuery("delete from quizrealizada where id="+ x + ";");
		tx.commit();
		return true;
	}

	public List<QuizRealizada> listarQuizRealizadas() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from QuizRealizada").list();
		t.commit();
		return lista;

	}

	public List<Usuario> listarUsuarioRealizadas() {

		
		Usuario user = new Usuario();	
		Session session = HibernateUtil.getSessionFactory().openSession();
		List lista = session.createQuery("from Usuario").list();
		
		System.out.println("---> "+ lista.isEmpty() );
		   return lista;
		   
	}
	
	
	public void update(QuizRealizada novaQuiz) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.update(novaQuiz);

	}

	public List<QuizRealizada> excluir(QuizRealizada quiz) {
		return null;
	}

	public Integer findIDPerguntaAtual() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("Abriu a session FindQuiz ID");
		Query query = session
				.createSQLQuery("select pergunta_id from quizrealizada where id_quiz = ( select max(id_quiz) from quizrealizada);");

		return (Integer) query.uniqueResult();
	}

	public Integer findIDQuizAtual() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("Abriu a session FindQuiz ID");
		Query query = session
				.createSQLQuery("select id_quiz from quizrealizada where id_quiz = ( select max(id_quiz) from quizrealizada);");

		return (Integer) query.uniqueResult();
	}

	public String findRespostaCorretaQuizAtual(int id_pergunta) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("Abriu a session FinQuiz ID");
		Query q = session.createSQLQuery("select respostacorreta from pergunta where id = "+ id_pergunta);

		return (String) q.uniqueResult().toString();
	}

	public QuizRealizada quizById(Integer id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		QuizRealizada retorno = (QuizRealizada) session
				.createCriteria(QuizRealizada.class)
				.add(Restrictions.eq("id_quiz", id)).uniqueResult();
		return retorno;
	}

	public String findConectividadeAtual() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		System.out.println("Abriu a session FindConectividade ID");
		Query query = session
				.createSQLQuery("select tipoconectividade from quizrealizada where id_quiz = ( select max(id_quiz) from quizrealizada);");

		return (String) query.uniqueResult();
	}

}

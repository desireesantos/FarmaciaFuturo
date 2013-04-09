package br.com.infraestrutura;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.com.bean.CadastrarPergunta;
import br.com.util.HibernateUtil;

public class PerguntaDAO implements InterfacePerguntaDAO {

	// M�todo respons�vel por salvar os dados no BD

	public Boolean salvar(CadastrarPergunta pergunta) {

		System.out.println("Entrou no DAO SALVAR !");
		Configuration configuration = new Configuration();
		configuration.configure();

		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();

		System.out.println("Session Factory" + session.getSessionFactory());

		Transaction tx = session.beginTransaction();
		session.save(pergunta);

		tx.commit();
		return true;
	}

	/*
	 * Obter dados para popular autoComplete da interface preBusca
	 * 
	 * @See permite consulta ao banco NO case sensytive
	 */

	@SuppressWarnings("unchecked")
	public List<String> listaTodos(String acharPalavra) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session
				.createQuery("select value from CadastrarPergunta where lower(value) like lower('%"
						+ acharPalavra + "%')");
		List<String> retorno = q.list();

		return retorno;

	}

	public List<CadastrarPergunta> listarPerguntas() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List lista = session.createQuery("from CadastrarPergunta").list();
		t.commit();
		return lista;

	}

	public void update(CadastrarPergunta x) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(x);
		System.out.println("ATUALIZOU");
		t.commit();

	}

	/**
	 * @param cadastrar
	 * @return lista de perguntas cadastras para atualizar a view
	 */
	public List<CadastrarPergunta> excluir(CadastrarPergunta x) {

		System.out.println(x.getId());
		System.out.println("SERA REMOVIDO  ... AGUARDE");
		Session sessionTemp = HibernateUtil.getSessionFactory().openSession();
		Transaction txTemp = sessionTemp.beginTransaction();
		sessionTemp.createSQLQuery("delete from quizrealizada where pergunta_id="+ x.getId());
		System.out.println("Apagou quiz");
		txTemp.commit();
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(x);
		System.out.println("Apagou pergunta");
		t.commit();
	    
		return listarPerguntas();

	}

	public Integer findByID(int id_pergunta) {
		System.out.println("ID PERF:" + id_pergunta);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query retorno = session.createQuery("select respostacorreta from pergunta where id="+id_pergunta);
		return (Integer)retorno.uniqueResult();

	}

}

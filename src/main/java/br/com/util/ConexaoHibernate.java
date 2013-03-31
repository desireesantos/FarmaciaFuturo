package br.com.util;
import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class ConexaoHibernate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//variavel que armazena os mapeamentos e configuracoes do Hibernate
	private static final SessionFactory sessionFactory;
	private static final ThreadLocal<Session> threadlocal = new ThreadLocal<Session>();
	static{
	try{
	sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	/*recebemos a configuracao do arquivo hibernate.cfg.xml que possui
	*dados da conexao
	* chamamos o metodo buildSessionFactory que retorna um objeto
	* session, que estabelece uma sessao de comunicacao com o BD
	* atraves de uma conexao JDBC */
	}catch (Throwable e) {
	throw new ExceptionInInitializerError(e);}
	}
	
	/* esse metodo associada cada sessao a um objeto, permitindo que
	 * cada sessao abra um processo concorrente  */
	public static Session getInstance(){
	Session session = (Session) threadlocal.get();
	session = sessionFactory.openSession();
	threadlocal.set(session);
	return session;}

}

package br.com.infraestrutura;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import br.com.bean.AdminLogado;
import br.com.bean.Administrador;
import br.com.service.InterfaceAdministrador;
import br.com.util.HibernateUtil;


/**
 * @author desireesantos
 *
 */
public class AdministradorDAO implements InterfaceAdministrador {
	

	/** 
	 * @param administrador
	 * @see Mï¿½todo respons‡vel por salvar os dados no BD
	 */
	@SuppressWarnings("deprecation")
	public boolean salvar(Administrador administrador) {   

		System.out.println("Entrou no AdminDAO !");
		Configuration configuration = new Configuration();
		configuration.configure();

		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();

		System.out.println("Nome do administrador:"+ administrador.getNome());
		System.out.println("Session Factory"+ session.getSessionFactory());
		
		Transaction tx = session.beginTransaction();
		session.save(administrador);
		
		tx.commit();
		return true;
		
	}
	
	
	public boolean salvarLogado( AdminLogado adminLogado) {   

		Configuration configuration = new Configuration();
		configuration.configure();

		
		@SuppressWarnings("deprecation")
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		
		Transaction tx = session.beginTransaction();
		session.save(adminLogado);
		
		tx.commit();
		return true;
		
	}
	
	public String  findLogadoByEmail() {
		 Session session = HibernateUtil.getSessionFactory().openSession(); 	
		 System.out.println("Abriu a session adminlogado");
		 Query query = session.createSQLQuery("select nome from adminlogado where id = ( select max(id) from adminlogado);");
		  String admin = (String) query.uniqueResult();
		 
		 return admin;
	}

	/**
	 * Obter dados para popular autoComplete da interface preBusca
	 * @See permite consulta ao banco NO case sensytive 
	 */
	@SuppressWarnings("unchecked")
	public List<String> acharNomeAdministrador( String acharNomeAdministrador) {
										
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 Query q = session.createQuery("select value from Administrador where lower(value) like lower('%"+ acharNomeAdministrador +"%')"); 		 
		 List<String> retorno = q.list();		
		 return retorno;
			      		
	}
	

	/**
	 * @return listar todos os usuï¿½rios cadastrados no banco de dados
	 */ 
	@SuppressWarnings("unchecked")
	public List<Administrador> listarAdministradors() {		
		 Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction t = session.beginTransaction();
	        List<Administrador> lista = session.createQuery("from Administrador").list();
	        t.commit();
	        return lista;	      
		
	}

	
	    /**
		 * @param administrador
		 * @return atualizar dados do administrador solicitado
		 */ 
	public void update(Administrador user) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction t = session.beginTransaction();
	        session.update(user);
	        System.out.println("ATUALIZOU");
	        t.commit();
	        
	    }

	

	 /**
	 * @param administrador
	 * @return lista de administradores cadastrados para atualizar a view
	 */
	public List<Administrador> excluir(Administrador user) {
		
		   System.out.println("SERA REMOVIDO  ... AGUARDE");		   
		   Session session = HibernateUtil.getSessionFactory().openSession();
	        Transaction t = session.beginTransaction();
	        session.delete(user);	        
	        t.commit();	       
	        return listarAdministradors();
		   
	    }


	 /**
	 * @param id
	 * @return administrador com o id solicitado
	 */
	public Administrador findByEmail(String email) {
		System.out.println("Entrou no findByEmail");
		 Session session = HibernateUtil.getSessionFactory().openSession(); 	
		 Transaction t = session.beginTransaction();
		 System.out.println("Abriu a session");
		 Administrador retorno = (Administrador) session.createCriteria(Administrador.class).add(Restrictions.eq("email", email)).uniqueResult();
		 return retorno;
	}


}

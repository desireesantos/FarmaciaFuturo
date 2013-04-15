package br.com.infraestrutura;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import br.com.bean.QuizRealizada;
import br.com.bean.Usuario;
import br.com.service.InterfaceUsuario;
import br.com.util.HibernateUtil;

/**
 * @author desireesantos
 * 
 */
public class UsuarioDAO implements InterfaceUsuario {

	/**
	 * @param usuario
	 * @see MŽtodo respons‡vel por salvar os dados no BD
	 */
	@SuppressWarnings("deprecation")
	public boolean salvar(Usuario usuario) {

		System.out.println("Entrou no UserDAO !");
		Configuration configuration = new Configuration();
		configuration.configure();

		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();

		System.out.println("Nome do usu‡rio:" + usuario.getNome());
		System.out.println("Session Factory" + session.getSessionFactory());

		Transaction tx = session.beginTransaction();
		session.save(usuario);

		tx.commit();
		return true;
	}

	/**
	 * Obter dados para popular autoComplete da interface preBusca
	 * 
	 * @See permite consulta ao banco NO case sensytive
	 */
	@SuppressWarnings("unchecked")
	public List<String> acharNomeUsuario(String acharNomeUsuario) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session
				.createQuery("select value from Usuario where lower(value) like lower('%"
						+ acharNomeUsuario + "%')");
		List<String> retorno = q.list();
		return retorno;

	}

	/**
	 * @return listar todos os usu‡rios cadastrados no banco de dados
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> listarUsuarios() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		List<Usuario> lista = session.createQuery("from Usuario").list();
		t.commit();
		return lista;

	}

	/**
	 * @param usuario
	 * @return atualizar dados do usu‡rio solicitado
	 */
	public void update(Usuario user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.update(user);
		System.out.println("ATUALIZOU");
		t.commit();

	}

	/**
	 * @param usuario
	 * @return lista de usuarios cadastrados para atualizar a view
	 */
	public List<Usuario> excluir(Usuario user) {

		System.out.println("SERA REMOVIDO  ... AGUARDE");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.delete(user);
		t.commit();
		return listarUsuarios();

	}

	/**
	 * @param id
	 * @return usuario com o id solicitado
	 */
	public Usuario findById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query q = session.createQuery("from Usuario where id");
		Usuario retorno = (Usuario) q.list();

		return retorno;
	}

	public Integer findQuizID() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query	= session.createSQLQuery("select id_quiz from quizrealizada where id_quiz = ( select max(id_quiz) from quizrealizada);");
		return (Integer) query.uniqueResult();
	}

	public int[] listarParticipantesQuiz(Integer quiz, String respostaCerta) {
		quiz = quiz - 1;
		
		System.out.println("Entrou no Usuarios QUIZDAO");
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Usuario> lista =  session.createSQLQuery("select * from participante").list();
	    
		
		int somarPontoCorreto = 0;
		int somarPontoErrado = 0;
        int pontos[]= {0,0};
		for(Iterator it= lista.iterator();it.hasNext();){  
                Object[] row = (Object[]) it.next();
                String resposta =  (String) row[4];
                Integer  id_quiz = (Integer) row[7];
              
               String stringIdQuiz =  String.valueOf(id_quiz);
               String stringQuiz =  String.valueOf(quiz);
                
               if (stringQuiz.equalsIgnoreCase(stringIdQuiz) && respostaCerta.equalsIgnoreCase(resposta)) {
				somarPontoCorreto++;
			} 
               if (stringQuiz.equalsIgnoreCase(stringIdQuiz) && !respostaCerta.equalsIgnoreCase(resposta)) {
            	somarPontoErrado++;
   			} 
            
		}
		
        pontos[0]=somarPontoCorreto;
        pontos[1]=somarPontoErrado;
        return pontos;	
				
	}

}

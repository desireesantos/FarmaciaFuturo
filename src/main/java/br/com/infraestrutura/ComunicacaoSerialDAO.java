package br.com.infraestrutura;

import java.util.Iterator;
import java.util.List;

import org.apache.tomcat.jni.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import br.com.bean.CadastrarPergunta;
import br.com.bean.Usuario;
import br.com.util.HibernateUtil;

import modelo.Dados;

public class ComunicacaoSerialDAO {

	public void usuariosOrigemCR(int idQuiz) {

		System.out.println("ENTROU USUARIOS_ORIGEM_cr");
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure();

		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<String> listaDados = session.createSQLQuery(
				"select * from dadoSerial").list();
		Usuario user = new Usuario();
		UsuarioDAO userDao = new UsuarioDAO();
 
		//System.out.println(" ===> " + listaDados.isEmpty() ); 
		int x = 1;
		for (Iterator it = listaDados.iterator(); it.hasNext();) {
			Object[] row = (Object[]) it.next();

			String resposta = (String) row[1];
			//System.out.println("ESTOU NO FOR");	
			user.setNome(resposta.substring(0, 3));
			user.setResposta(resposta.substring(3, 4));
			user.setQuiz(idQuiz);
			userDao.salvar(user);
			System.out.println("Imprimir valor na tela CRS: "+ user.getNome() + " - "+ user.getResposta());			
			
		}

		
		tx.commit();
		

	}

	private void deletarUmRegistro() {
		
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure();

		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();


		@SuppressWarnings("unchecked")
		Query query = session.createSQLQuery("delete from participante where id=  ( select max(id) from participante);");
		
		tx.commit();
		
	}

	public void excluirTudo() {

		
		Configuration configuration = new Configuration();
		configuration.configure();

		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();
		Session session = factory.openSession();

		Transaction tx = session.beginTransaction();
		session.createSQLQuery("delete from dadoSerial").executeUpdate();
		System.out.println("Deve apagar");
		tx.commit();

	}

}

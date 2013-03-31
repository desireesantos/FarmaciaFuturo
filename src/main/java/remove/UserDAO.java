package remove;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.bean.Usuario;
import br.com.util.ConexaoHibernate;



public class UserDAO    {

	private Session session;

	// metodo responsável por salvar os dados no BD
	public boolean salvar(Usuario usuario) {
		session = ConexaoHibernate.getInstance();// cria uma sessao
		Transaction tx = null; // permite transacoes no BD

		try {
			tx = session.beginTransaction();
			session.save(usuario);// grava no BD
			tx.commit();// transacao efetuada
		} catch (HibernateException e) {
			System.out.println("Verificar erro");
			e.printStackTrace();
			// tem o objetivo de desfazer a transcao em caso de erro
			tx.rollback();
		} finally {
			session.close();
		}
		return false;
	}

	
	
}

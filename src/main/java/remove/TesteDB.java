package remove;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.bean.CadastrarPergunta;





public class TesteDB {
	
	public static void main(String[] args) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("cliqueonDB"); 
		EntityManager em = factory.createEntityManager();	
		
		CadastrarPergunta cp = new CadastrarPergunta();
		
		
		
		em.getTransaction().begin();
		em.persist(cp);  
		em.getTransaction().commit();
		
	}
	
	
	
  
}

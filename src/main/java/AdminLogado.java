

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;


/**
 * Entity implementation class for Entity: Participante
 *
 */
@Entity
public class AdminLogado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id()
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_adminLogado")
	@SequenceGenerator(sequenceName="id_adminLogado", name="id_adminLogado", allocationSize=0)
	private int id;
	
	private String nome;
	private String email;
	
	public AdminLogado() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

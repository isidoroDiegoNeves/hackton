package consorcio.server;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmailControl {
	
	@Id
	public String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}

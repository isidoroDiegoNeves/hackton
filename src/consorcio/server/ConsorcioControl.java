package consorcio.server;


import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.api.server.spi.config.ApiMethod;

@Entity
public class ConsorcioControl {
	
	@Id
	public String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@ApiMethod
	public String enviaEmail(){
	
		return "Sucesso";
	}
	
}

package consorcio.server;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ParticipanteEnderecosBean {
	
	@Id
	private String cod_participante;
	private String cod_address;
	private String item;
	public String getCod_participante() {
		return cod_participante;
	}
	public void setCod_participante(String cod_participante) {
		this.cod_participante = cod_participante;
	}
	public String getCod_address() {
		return cod_address;
	}
	public void setCod_address(String cod_address) {
		this.cod_address = cod_address;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
}

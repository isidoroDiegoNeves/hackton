package consorcio.server;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ParticipanteEnderecosBean {
	
	@Id
	private Integer cod_participante;
	private Integer cod_address;
	private Integer item;
	public Integer getCod_participante() {
		return cod_participante;
	}
	public void setCod_participante(Integer cod_participante) {
		this.cod_participante = cod_participante;
	}
	public Integer getCod_address() {
		return cod_address;
	}
	public void setCod_address(Integer cod_address) {
		this.cod_address = cod_address;
	}
	public Integer getItem() {
		return item;
	}
	public void setItem(Integer item) {
		this.item = item;
	}
	
}

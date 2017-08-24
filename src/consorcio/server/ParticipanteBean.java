package consorcio.server;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ParticipanteBean {
	
	@Id
	private Integer cod;
	private String ds_name;
	private String do_type;
	private Integer cod_document;
	private String email;
	private String ds_country;
	private String ds_uf;
	public Integer getCod() {
		return cod;
	}
	public void setCod(Integer cod) {
		this.cod = cod;
	}
	public String getDs_name() {
		return ds_name;
	}
	public void setDs_name(String ds_name) {
		this.ds_name = ds_name;
	}
	public String getDo_type() {
		return do_type;
	}
	public void setDo_type(String do_type) {
		this.do_type = do_type;
	}
	public Integer getCod_document() {
		return cod_document;
	}
	public void setCod_document(Integer cod_document) {
		this.cod_document = cod_document;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDs_country() {
		return ds_country;
	}
	public void setDs_country(String ds_country) {
		this.ds_country = ds_country;
	}
	public String getDs_uf() {
		return ds_uf;
	}
	public void setDs_uf(String ds_uf) {
		this.ds_uf = ds_uf;
	}
}
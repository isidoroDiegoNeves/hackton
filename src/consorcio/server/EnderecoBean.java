package consorcio.server;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EnderecoBean {
	
	@Id
	private String cod;
	private String ds_address;
	private String ds_place;
	private String ds_number;
	private String ds_neighborhood;
	private String ds_city;
	private String ds_country;
	private String ds_complement;
	private String do_type;
	private String do_main;
	
	
	public String getDs_address() {
		return ds_address;
	}
	public void setDs_address(String ds_address) {
		this.ds_address = ds_address;
	}
	public String getDs_place() {
		return ds_place;
	}
	public void setDs_place(String ds_place) {
		this.ds_place = ds_place;
	}
	public String getDs_number() {
		return ds_number;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public void setDs_number(String ds_number) {
		this.ds_number = ds_number;
	}
	public String getDs_neighborhood() {
		return ds_neighborhood;
	}
	public void setDs_neighborhood(String ds_neighborhood) {
		this.ds_neighborhood = ds_neighborhood;
	}
	public String getDs_city() {
		return ds_city;
	}
	public void setDs_city(String ds_city) {
		this.ds_city = ds_city;
	}
	public String getDs_country() {
		return ds_country;
	}
	public void setDs_country(String ds_country) {
		this.ds_country = ds_country;
	}
	public String getDs_complement() {
		return ds_complement;
	}
	public void setDs_complement(String ds_complement) {
		this.ds_complement = ds_complement;
	}
	public String getDo_type() {
		return do_type;
	}
	public void setDo_type(String do_type) {
		this.do_type = do_type;
	}
	public String getDo_main() {
		return do_main;
	}
	public void setDo_main(String do_main) {
		this.do_main = do_main;
	}
}

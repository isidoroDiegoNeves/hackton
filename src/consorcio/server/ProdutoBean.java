package consorcio.server;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProdutoBean {
	
	@Id
	private String cod;
	private String ds_name;
	private String ds_description;
	private String do_type;
	private String ds_value;
	private String ds_unit;
	public String getDs_name() {
		return ds_name;
	}
	public void setDs_name(String ds_name) {
		this.ds_name = ds_name;
	}
	public String getDs_description() {
		return ds_description;
	}
	public void setDs_description(String ds_description) {
		this.ds_description = ds_description;
	}
	public String getDs_value() {
		return ds_value;
	}
	public void setDs_value(String ds_value) {
		this.ds_value = ds_value;
	}
	public String getDs_unit() {
		return ds_unit;
	}
	public void setDs_unit(String ds_unit) {
		this.ds_unit = ds_unit;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getDo_type() {
		return do_type;
	}
	public void setDo_type(String do_type) {
		this.do_type = do_type;
	}
	
}

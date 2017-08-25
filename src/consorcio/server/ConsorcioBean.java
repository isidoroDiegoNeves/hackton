package consorcio.server;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConsorcioBean {
	
	@Id
	private String cod;
	private String ds_name;
	private String ds_description;
	private String cod_product;
	private String do_publish;
	private String dt_lottery;
	private Integer nro_lottery;
	private Integer qt_parcels;
	private Integer qt_contemplated_lottery;
	private Integer qt_contemplated_throw;
	private String ds_cancellation_reason;
	private String cod_responsible;
	private String dt_created;
	private String cod_usercreated;
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
	public String getDo_publish() {
		return do_publish;
	}
	public void setDo_publish(String do_publish) {
		this.do_publish = do_publish;
	}
	public Integer getNro_lottery() {
		return nro_lottery;
	}
	public void setNro_lottery(Integer nro_lottery) {
		this.nro_lottery = nro_lottery;
	}
	public Integer getQt_parcels() {
		return qt_parcels;
	}
	public void setQt_parcels(Integer qt_parcels) {
		this.qt_parcels = qt_parcels;
	}
	public Integer getQt_contemplated_lottery() {
		return qt_contemplated_lottery;
	}
	public void setQt_contemplated_lottery(Integer qt_contemplated_lottery) {
		this.qt_contemplated_lottery = qt_contemplated_lottery;
	}
	public Integer getQt_contemplated_throw() {
		return qt_contemplated_throw;
	}
	public void setQt_contemplated_throw(Integer qt_contemplated_throw) {
		this.qt_contemplated_throw = qt_contemplated_throw;
	}
	public String getDs_cancellation_reason() {
		return ds_cancellation_reason;
	}
	public void setDs_cancellation_reason(String ds_cancellation_reason) {
		this.ds_cancellation_reason = ds_cancellation_reason;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getCod_product() {
		return cod_product;
	}
	public void setCod_product(String cod_product) {
		this.cod_product = cod_product;
	}
	public String getDt_lottery() {
		return dt_lottery;
	}
	public void setDt_lottery(String dt_lottery) {
		this.dt_lottery = dt_lottery;
	}
	public String getCod_responsible() {
		return cod_responsible;
	}
	public void setCod_responsible(String cod_responsible) {
		this.cod_responsible = cod_responsible;
	}
	public String getDt_created() {
		return dt_created;
	}
	public void setDt_created(String dt_created) {
		this.dt_created = dt_created;
	}
	public String getCod_usercreated() {
		return cod_usercreated;
	}
	public void setCod_usercreated(String cod_usercreated) {
		this.cod_usercreated = cod_usercreated;
	}
}

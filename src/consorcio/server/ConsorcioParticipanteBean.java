package consorcio.server;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConsorcioParticipanteBean {
	
	@Id
	private String cod_consorcio;
	private Integer cod_participante;
	private String dt_lottery;
	private Integer nro_lottery;
	private String do_status;
	private BigDecimal ds_value;
	
	public String getCod_consorcio() {
		return cod_consorcio;
	}
	public void setCod_consorcio(String cod_consorcio) {
		this.cod_consorcio = cod_consorcio;
	}
	public Integer getCod_participante() {
		return cod_participante;
	}
	public void setCod_participante(Integer cod_participante) {
		this.cod_participante = cod_participante;
	}
	public Integer getNro_lottery() {
		return nro_lottery;
	}
	public void setNro_lottery(Integer nro_lottery) {
		this.nro_lottery = nro_lottery;
	}
	public String getDo_status() {
		return do_status;
	}
	public void setDo_status(String do_status) {
		this.do_status = do_status;
	}
	public BigDecimal getDs_value() {
		return ds_value;
	}
	public void setDs_value(BigDecimal ds_value) {
		this.ds_value = ds_value;
	}
	public String getDt_lottery() {
		return dt_lottery;
	}
	public void setDt_lottery(String dt_lottery) {
		this.dt_lottery = dt_lottery;
	}

}

package consorcio.server;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConsorcioParticipanteBean {
	
	@Id
	private Integer cod_consorcio;
	private Integer cod_participante;
	private Date dt_lottery;
	private Integer nro_lottery;
	private String do_status;
	private BigDecimal ds_value;
	public Integer getCod_consorcio() {
		return cod_consorcio;
	}
	public void setCod_consorcio(Integer cod_consorcio) {
		this.cod_consorcio = cod_consorcio;
	}
	public Integer getCod_participante() {
		return cod_participante;
	}
	public void setCod_participante(Integer cod_participante) {
		this.cod_participante = cod_participante;
	}
	public Date getDt_lottery() {
		return dt_lottery;
	}
	public void setDt_lottery(Date dt_lottery) {
		this.dt_lottery = dt_lottery;
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

}

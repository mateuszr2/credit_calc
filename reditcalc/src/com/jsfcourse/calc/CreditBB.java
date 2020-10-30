package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class CreditBB {
	private Double kwota;
	private Integer miesiac;
	private Double procent;
	private Double result;

	@Inject
	FacesContext ctx;

	



	public Double getKwota() {
		return kwota;
	}

	public void setKwota(Double kwota) {
		this.kwota = kwota;
	}

	public Integer getMiesiac() {
		return miesiac;
	}

	public void setMiesiac(Integer miesiac) {
		this.miesiac = miesiac;
	}

	public Double getProcent() {
		return procent;
	}

	public void setProcent(Double procent) {
		this.procent = procent;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public boolean doTheMath() {
		try {
			double kwota = this.kwota;
			double miesiac = this.miesiac;
			double procent = this.procent;
			
			kwota = (kwota / miesiac);
			result = kwota+(kwota * procent/100);

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³¹d podczas przetwarzania parametrów", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Miesiêczna rata to: " + result, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}

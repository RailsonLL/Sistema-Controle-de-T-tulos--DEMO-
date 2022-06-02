package project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import project.bean.geral.ObjetoCampoConsulta;

@FacesConverter(forClass = ObjetoCampoConsulta.class)
public class ObjetoCampoConsultaConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent componests, String value) {
		if (value != null && !value.isEmpty()) {
			String[] vals = value .split("\\*");
			ObjetoCampoConsulta obConsulta = new ObjetoCampoConsulta();
			obConsulta.setCampoBanco(vals[0]);
			obConsulta.setTipoClass(vals[1]);
			return obConsulta;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			ObjetoCampoConsulta c = (ObjetoCampoConsulta) value;
			return c.getCampoBanco() + "*" + c.getTipoClass();
		} else {
			return "Não foi possível estabelecer o valor";
		}
	}

}

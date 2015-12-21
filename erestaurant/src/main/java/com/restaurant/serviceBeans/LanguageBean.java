/*package com.restaurant.serviceBeans;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@ApplicationScoped
public class LanguageBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String localeCode;

	private static Map<String,Object> countries;
	static{
		countries = new LinkedHashMap<String,Object>();
		countries.put("English", Locale.ENGLISH); //label, value
		countries.put("Українська", new Locale("uk", "UA"));
	}

	public Map<String, Object> getCountriesInMap() {
		return countries;
	}


	public String getLocaleCode() {
		return localeCode;
	}


	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	//value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e){

		String newLocaleValue = e.getNewValue().toString();

		//loop country map to compare the locale code
		for (Map.Entry<String, Object> entry : countries.entrySet()) {

			if(entry.getValue().toString().equals(newLocaleValue)){

				FacesContext.getCurrentInstance()
				.getViewRoot().setLocale((Locale)entry.getValue());
			}
		}
	}
	
		 <h:selectOneMenu value="#{languageBean.localeCode}"
				onchange="submit()"
				valueChangeListener="#{languageBean.countryLocaleCodeChanged}">
				<f:selectItems value="#{languageBean.countriesInMap}" />
			</h:selectOneMenu> 
	*
	
	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

	public LanguageBean(){
	}
	
	public Locale getLocale() {
		return locale;
	}

	public String getLanguage() {
		return locale.getLanguage();
	}

	public void changeLanguage(String language) {
		locale = new Locale(language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
		if(language.equals("UA")){
			FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("uk", "UA"));
		}
	}

}
*/
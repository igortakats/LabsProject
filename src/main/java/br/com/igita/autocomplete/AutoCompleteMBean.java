package br.com.igita.autocomplete;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AutoCompleteMBean implements Serializable{

	private static final long serialVersionUID = -3324872119097054216L;
	
	private List<String> valores = new ArrayList<String>();

	public AutoCompleteMBean (){

	}
	
	public List<String> complete(String query){
		List<String> queries = new ArrayList<String>();
		for(int i = 0 ; i < 15 ; i++){
			queries.add(query+i);
		}
		return queries;
	}

	public List<String> getValores() {
		return valores;
	}

	public void setValores(List<String> valores) {
		this.valores = valores;
	}

	public String getValoresDigitados() {
		
		StringBuilder b = new StringBuilder();
		
		if (valores != null) {
			for (String s : valores) {
				b.append(s);
				b.append(", ");
			}
		}
		
		return  b.toString();
		
	}

}
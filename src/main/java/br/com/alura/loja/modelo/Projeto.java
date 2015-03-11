package br.com.alura.loja.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Projeto {
	
	private String nome;
	private Long id;
	private int anoDeInicio;
	
	public Projeto() {}
	
	public Projeto(Long id, String nome, int anoDeInicio) {
		this.nome = nome;
		this.id = id;
		this.anoDeInicio = anoDeInicio;
	}

	public String getNome() {
		return nome;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public int getAnoDeInicio() {
		return anoDeInicio;
	}

	public String toXml() {
		XStream stream = new XStream();
//		stream.alias("projeto", Projeto.class);
		return stream.toXML(this);
	}

	public String toJason() {
		return new Gson().toJson(this);
	}
	
	
	

}

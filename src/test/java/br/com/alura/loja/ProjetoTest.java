package br.com.alura.loja;

import static org.junit.Assert.*;

import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Projeto;

import com.thoughtworks.xstream.XStream;

public class ProjetoTest {
	
	private HttpServer inicializaServidor;
	private Client client;


	@Before
	public void starServidor() throws URISyntaxException{
		inicializaServidor = Servidor.inicializaServidor();
		
		ClientConfig config = new ClientConfig();
		config.register(new LoggingFilter());		
		this.client = ClientBuilder.newClient(config);
	}
	
	@After
	public void stopServer() {
		inicializaServidor.stop();
	}
	
	@Test
	public void testaQueAConexaoComOServidorFuncionaNoPathDeProjetos() {

		WebTarget target = client.target("http://localhost:8080");
		Projeto projeto = target.path("/projeto/1").request().get(Projeto.class);
		//XStream stream = new XStream();
		//stream.alias("projeto", Projeto.class);
		//Projeto projeto = (Projeto) stream.fromXML(conteudo);
		
		assertEquals("Minha loja", projeto.getNome());
		
		
		
	}

}

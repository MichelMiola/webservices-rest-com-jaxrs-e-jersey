package br.com.alura.loja;

import static org.junit.Assert.*;

import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

import com.thoughtworks.xstream.XStream;

public class CarrinhoTest {
	
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
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
		
		WebTarget target = this.client.target("http://localhost:8080");		
		Carrinho carrinho = target.path("/carrinhos/1").request().get(Carrinho.class);
		
		//System.out.println(conteudo);
		
//		XStream stream = new XStream();
//		stream.alias("carrinho", Carrinho.class);
//		stream.alias("produto", Produto.class);
//		Carrinho carrinho = (Carrinho) stream.fromXML(conteudo);
		assertEquals("Videogame 4", carrinho.getProdutos().get(0).getNome());
	}
	
	@Test
	public void testeQueInsericarinho() {
		
        WebTarget target = client.target("http://localhost:8080");
        
        Carrinho carrinho = new Carrinho();
        carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
        carrinho.setRua("Rua Vergueiro");
        carrinho.setCidade("Sao Paulo");
        //String xml = carrinho.toXml();
        
        //System.out.println(xml);
        
        Entity<Carrinho> entity = Entity.entity(carrinho, MediaType.APPLICATION_XML);

        Response response = target.path("/carrinhos").request().post(entity);
        /*assertEquals("<status>sucesso</status>", response.readEntity(String.class));*/
        assertEquals(201, response.getStatus());
        
        String location = response.getHeaderString("Location");
        Carrinho carrinhoCarrgado = client.target(location).request().get(Carrinho.class);
        assertEquals("Tablet", carrinhoCarrgado.getProdutos().get(0).getNome());
		
	}
	
}

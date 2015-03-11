package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {
	
	public static void main(String[] args) {
		
		try {
			
			HttpServer server = inicializaServidor();
			System.out.println("Servdor Rodando");
			System.in.read();
			server.stop();
			
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		
	}

	public static HttpServer inicializaServidor() throws URISyntaxException{
		ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja");
		URI uri = new URI("http://localhost:8080/");
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		return server;
	}

}

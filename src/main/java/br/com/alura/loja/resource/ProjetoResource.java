package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;

@Path("projeto")
public class ProjetoResource {
	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Projeto busca(@PathParam("id") Long id) {
		Projeto projeto = new ProjetoDAO().busca(id);
		return projeto;
	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(Projeto projeto) {
		//Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		new ProjetoDAO().adiciona(projeto);
		String uri = "/projeto/" + projeto.getId();
		URI create = URI.create(uri);
		return Response.created(create).build();
	}
	
	@Path("{id}")
    @DELETE
    public Response removeProjeto(@PathParam("id") long id) {
        new ProjetoDAO().remove(id);
        return Response.ok().build();
    }
	
}

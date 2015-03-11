package br.com.alura.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

@Path("carrinhos")
public class CarrinhoResource {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	// Recebe por parametro {server/id=1}
	// public String busca(@QueryParam("id") long id) {
	public Carrinho busca(@PathParam("id") Long id) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		return carrinho;

	}

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(Carrinho carrinho) {
		//Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(carrinho);
		String uri = "/carrinhos/" + carrinho.getId();
		URI create = URI.create(uri);
		return Response.created(create).build();

	}

	@Path("{id}/produtos/{produtoId}")
	@DELETE
	public Response remove(@PathParam("id") Long id, @PathParam("produtoId") Long produtoId) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(produtoId);
		return Response.ok().build();

	}

	@Path("{id}/produtos/{produtoId}")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response alteraProduto(@PathParam("id") long id,	@PathParam("produtoId") long produtoId, String conteudo) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		Produto produto = (Produto) new XStream().fromXML(conteudo);
		carrinho.troca(produto);
		return Response.ok().build();
	}
	
	@Path("{id}/produtos/{produtoId}/quantidade")
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response alteraQuantidade(@PathParam("id") long id, @PathParam("produtoId") long produtoId, Produto produto) {
        Carrinho carrinho = new CarrinhoDAO().busca(id);
        //Produto produto = (Produto) new XStream().fromXML(conteudo);
        carrinho.trocaQuantidade(produto);
        return Response.ok().build();
    }

}
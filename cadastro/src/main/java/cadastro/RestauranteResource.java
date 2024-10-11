package cadastro;

import java.util.List;
import java.util.Optional;


import dto.AdicionarRestauranteDTO;
import dto.RestauranteMapper;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    @Inject
    RestauranteMapper restauranteMapper;

    @GET    
    public List<Restaurante> buscar() {
        return Restaurante.listAll();
    }

    @POST
    @Transactional
    public Response adicionar(AdicionarRestauranteDTO dto) {
      Restaurante restaurante = restauranteMapper.toRestaurante(dto);
        restaurante.persist();
        return Response.status(Response.Status.CREATED).build();
    }
    
    
    @PUT
    @Path("{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, Restaurante dto){
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
        if (restauranteOp.isEmpty()){
            throw new NotFoundException();
        }
        Restaurante restaurante = restauranteOp.get();
        restaurante.nome = dto.nome;
        restaurante.persist();
    }
    
    @DELETE
    @Path("{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id, Restaurante dtoRestaurante){
        Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
        restauranteOp.ifPresentOrElse(Restaurante::delete, ()->{
            throw new NotFoundException();
        });

    }
    
}

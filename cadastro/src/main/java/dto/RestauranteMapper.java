package dto;

import cadastro.Restaurante;


import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

    Restaurante toRestaurante(AdicionarRestauranteDTO dto);
}

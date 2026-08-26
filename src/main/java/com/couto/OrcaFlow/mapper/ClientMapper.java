package com.couto.OrcaFlow.mapper;

import com.couto.OrcaFlow.domin.Cliente;
import com.couto.OrcaFlow.dto.ClientRequest;
import com.couto.OrcaFlow.dto.ClienteResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {


    ClienteResponse toDto(Cliente cliente);

    Cliente toEntity(ClientRequest request);


}

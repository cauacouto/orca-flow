package com.couto.OrcaFlow.mapper;

import com.couto.OrcaFlow.domin.Orcamento;
import com.couto.OrcaFlow.dto.OrcamentoRequest;
import com.couto.OrcaFlow.dto.OrcamentoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrcamentoMapper {

    Orcamento toEntity (OrcamentoRequest request);

    OrcamentoResponse toDto(Orcamento orcamento);


}

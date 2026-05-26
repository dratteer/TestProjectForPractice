package com.bohdan.training.TestProjectForPractice.mapper;

import com.bohdan.training.TestProjectForPractice.dto.Response.ClientDto;
import com.bohdan.training.TestProjectForPractice.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto toDto(Client client);
    Client toEntity(ClientDto clientDto);
    List<ClientDto> toDtoList(List<Client> clients);

    @Mapping(target = "id", ignore = true)
    void updateClientFromDto(ClientDto dto, @MappingTarget Client entity);
}

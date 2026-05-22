package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.ClientDto;
import com.bohdan.training.TestProjectForPractice.dto.IdDto;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAll();

    ClientDto getById(Long id);

    IdDto create(ClientDto dto);

    void update(Long id, ClientDto updatedDto);

    void delete(Long id);
}

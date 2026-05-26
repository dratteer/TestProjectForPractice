package com.bohdan.training.TestProjectForPractice.service;

import com.bohdan.training.TestProjectForPractice.dto.Response.ClientDto;
import com.bohdan.training.TestProjectForPractice.dto.IdDto;
import com.bohdan.training.TestProjectForPractice.entity.Client;
import com.bohdan.training.TestProjectForPractice.mapper.ClientMapper;
import com.bohdan.training.TestProjectForPractice.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public List<ClientDto> getAll() {
        List<Client> clients = clientRepository.findAll();

        return clientMapper.toDtoList(clients);
    }

    @Override
    public ClientDto getById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return clientMapper.toDto(client);
    }

    @Override
    public IdDto create(ClientDto dto) {
        Client entity = clientMapper.toEntity(dto);
        Client saved = clientRepository.save(entity);

        IdDto idDto = new IdDto();
        idDto.setId(saved.getId());
        return idDto;
    }

    @Override
    public void update(Long id, ClientDto updatedDto) {
        Client existing = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        clientMapper.updateClientFromDto(updatedDto, existing);
        clientRepository.save(existing);
    }

    @Override
    public  void delete(Long id) {
        clientRepository.deleteById(id);
    }
}

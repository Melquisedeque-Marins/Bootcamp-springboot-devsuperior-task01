package com.mck.task01.servicies;

import com.mck.task01.dtos.ClientDTO;
import com.mck.task01.entities.Client;
import com.mck.task01.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientDTO create(ClientDTO dto) {
        Client client = new Client();
        BeanUtils.copyProperties(dto, client);
        clientRepository.save(client);
        return new ClientDTO(client);
    }
}

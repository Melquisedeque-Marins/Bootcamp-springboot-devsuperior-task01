package com.mck.task01.servicies;

import com.mck.task01.dtos.ClientDTO;
import com.mck.task01.entities.Client;
import com.mck.task01.repositories.ClientRepository;
import com.mck.task01.servicies.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional
    public List<ClientDTO> findAll() {
        List<Client> list = clientRepository.findAll();
        return list.stream().map(ClientDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public ClientDTO findById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        Client entity = client.orElseThrow(() -> new ResourceNotFoundException("the Client with id: " + id + " could not be founded"));
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(ClientDTO dto, Long id) {
       try {
           Client client = clientRepository.getReferenceById(id);
           dto.setId(client.getId());
           BeanUtils.copyProperties(dto, client);
           clientRepository.save(client);
           return new ClientDTO(client);
       } catch (BeansException e) {
           throw new ResourceNotFoundException("the Client with id: " + id + " could not be founded");
       }

    }
}

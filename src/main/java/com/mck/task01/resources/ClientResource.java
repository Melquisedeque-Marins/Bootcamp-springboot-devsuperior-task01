package com.mck.task01.resources;

import com.mck.task01.dtos.ClientDTO;
import com.mck.task01.servicies.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientResource {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO dto){
        dto = clientService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        List<ClientDTO> listDto = clientService.findAll();
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        ClientDTO dto = clientService.findById(id);
        return  ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO dto, @PathVariable Long id){
        ClientDTO dtoUpdated = clientService.update(dto, id);
        return ResponseEntity.ok().body(dtoUpdated);
    }
}

package com.devsuperior.rest_crud.services;

import com.devsuperior.rest_crud.dto.ClientDTO;
import com.devsuperior.rest_crud.entities.Client;
import com.devsuperior.rest_crud.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        return repository.findAll(pageable).map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        return new ClientDTO(repository.findById(id).get());
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client client = new Client();
        copyDTOtoEntity(client, dto);
        return new ClientDTO(repository.save(client));
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        Client client = repository.getReferenceById(id);
        copyDTOtoEntity(client, dto);
        return new ClientDTO(repository.save(client));
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    private void copyDTOtoEntity(Client entity, ClientDTO dto){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}

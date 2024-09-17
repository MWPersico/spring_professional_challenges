package com.devsuperior.rest_crud.services;

import com.devsuperior.rest_crud.dto.ClientDTO;
import com.devsuperior.rest_crud.entities.Client;
import com.devsuperior.rest_crud.exceptions.ResourceNotFoundException;
import com.devsuperior.rest_crud.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
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
        return new ClientDTO(repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Client not found")));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client client = new Client();
        copyDTOtoEntity(client, dto);
        return new ClientDTO(repository.save(client));
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        try{
            Client client = repository.getReferenceById(id);
            copyDTOtoEntity(client, dto);
            return new ClientDTO(repository.save(client));
        }catch(EntityNotFoundException ex){throw new ResourceNotFoundException("Client not found for update");}
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Client not found for deletion");
        }else{
            repository.deleteById(id);
        }
    }

    private void copyDTOtoEntity(Client entity, ClientDTO dto){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}

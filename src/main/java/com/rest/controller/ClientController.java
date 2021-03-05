package com.rest.controller;

import com.rest.model.Client;
import com.rest.service.ClientService;
import com.rest.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    private final IClientService clientService;

    @Autowired
    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    public ResponseEntity<?> save(Client client) {
        clientService.save(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> read() {
        final List<Client> clients = clientService.readAll();
        ResponseEntity<List<Client>> responseEntity;
        if (clients != null && !clients.isEmpty()) {
            responseEntity = new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<List<Client>>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<Client> read(@PathVariable(name = "id") int id) {
        final Client client = clientService.read(id);
        ResponseEntity<Client> responseEntity;
        if (client != null) {
            responseEntity = new ResponseEntity<Client>(client, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Client client) {
        return clientService.update(client, id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        return clientService.delete(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}

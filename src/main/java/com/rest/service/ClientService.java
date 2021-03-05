package com.rest.service;

import com.rest.model.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ClientService implements IClientService {

    private static final Map<Integer, Client> REPOSITORY_MAP = new HashMap<>();

    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();

    @Override
    public void save(Client client) {
        int id = CLIENT_ID_HOLDER.incrementAndGet();
        client.setId(id);
        REPOSITORY_MAP.put(id, client);
    }

    @Override
    public List<Client> readAll() {
        return new ArrayList<>(REPOSITORY_MAP.values());
    }

    @Override
    public Client read(int id) {
        return REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Client client, int id) {
        if (REPOSITORY_MAP.containsKey(id)) {
            client.setId(id);
            REPOSITORY_MAP.put(id, client);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return REPOSITORY_MAP.remove(id) != null;
    }
}

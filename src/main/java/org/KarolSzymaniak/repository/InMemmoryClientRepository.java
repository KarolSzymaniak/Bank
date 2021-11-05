package org.KarolSzymaniak.repository;

import org.KarolSzymaniak.Client;

import java.util.Set;

public class InMemmoryClientRepository implements ClientRepository {

    private Set<Client> clients;

    public InMemmoryClientRepository(Set<Client> clients) {
        this.clients = clients;
    }

    @Override
    public void saveClient(Client client) {
        clients.add(client);
    }

    @Override
    public Client findByEmail(String email) {
        return clients
                .stream()
                .filter(client -> client.getEmail().equals(email))
                .findFirst()
                .get();
    }
}
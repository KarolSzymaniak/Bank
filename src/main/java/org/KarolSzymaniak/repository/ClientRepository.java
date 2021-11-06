package org.KarolSzymaniak.repository;

import org.KarolSzymaniak.entity.Client;

public interface ClientRepository {

    void saveClient(Client client);

    Client findByEmail(String email);


}
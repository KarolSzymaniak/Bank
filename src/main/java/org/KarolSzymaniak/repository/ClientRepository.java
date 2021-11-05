package org.KarolSzymaniak.repository;

import org.KarolSzymaniak.Client;

public interface ClientRepository {

    void saveClient(Client client);

    Client findByEmail(String email);


}
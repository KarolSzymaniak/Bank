package KarolSzymaniak.org.repository;

import KarolSzymaniak.org.Client;

public interface ClientRepository {

    void saveClient(Client client);

    Client findByEmail(String email);


}
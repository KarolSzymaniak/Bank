package org.KarolSzymaniak.repository;

import org.KarolSzymaniak.Client;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class HibernateClientRepository implements ClientRepository {
    @Override
    public void saveClient(Client client) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public Client findByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Client> query = session.createQuery("from Client where mail =:mail", Client.class);

        query.setParameter("mail",email);
        Client client = query.uniqueResult();
        //do poprawy sposób zamykania sesji - powinno być w try with resorses
        session.close();
        return  client;
    }
}

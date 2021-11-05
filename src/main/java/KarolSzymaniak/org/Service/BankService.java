package KarolSzymaniak.org.Service;

import KarolSzymaniak.org.Client;
import KarolSzymaniak.org.repository.ClientRepository;

import java.util.Objects;


    public class BankService {
        private ClientRepository clientRepository;

        public BankService(ClientRepository clientRepository) {
            this.clientRepository = clientRepository;
        }


        public  void save(Client client){
            if (Objects.isNull(client.getName())){
                throw new IllegalArgumentException("Imie nie może być puste ! ! !");
            }
            if (Objects.isNull(client.getEmail())){
                throw new IllegalArgumentException("Email nie może być pusty ! ! !");
            }

            if (clientRepository.findByEmail(client.getEmail())== null){
                throw new IllegalArgumentException("Klient już istnieje");
            }
            clientRepository.saveClient(client);
        }

        public  Client findByEmail(String email){

            return clientRepository.findByEmail(email);
        }

//    public void transfer(String fromEmail, String toEmail, double amount) {
//        Client fromClient = findByEmail(fromEmail);
//        Client toClient = findByEmail(toEmail);
//
//        if(amount>0 && fromClient.getBalance() - amount>=0){
//            fromClient.setBalance(fromClient.getBalance()-amount);
//            toClient.setBalance(toClient.getBalance()+amount);
//        }
//        else {
//
//            throw new NoSufficientFoundException("Not enought money");
//        }
//
//    }





        public void transfer(String fromEmail, String toEmail, double amount)
        {
            validateAmount(amount);
            if (fromEmail.equals(toEmail)) {
                throw new IllegalArgumentException("fromEmail and toEmail cant be equal!");
            }
            Client fromClient = findByEmail(fromEmail);
            Client toClient = findByEmail(toEmail);
            if (fromClient.getBalance() - amount >= 0) {
                fromClient.setBalance(fromClient.getBalance() - amount);
                toClient.setBalance(toClient.getBalance() + amount);
            } else {
                throw new NoSufficientFoundException("Not enough funds!");
            }
        }
        public void withdraw(
                final String email,
                final double amount) {
            validateAmount(amount);
            if (Objects.isNull(email)) {
                throw new IllegalArgumentException("Email cant be null!");
            }
            final String lowerCaseEmail = email.toLowerCase();
            final Client client = findByEmail(lowerCaseEmail);
            if (amount > client.getBalance()) {
                throw new NoSufficientFoundException("Balance must be higher or equal then amount!");
            }
            final double newBalance = client.getBalance() - amount;
            client.setBalance(newBalance);

        }

        private void validateAmount(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Amount must be positive!");
            }
        }

}

import org.KarolSzymaniak.Client;
import org.KarolSzymaniak.Service.BankService;
import org.KarolSzymaniak.repository.ClientRepository;
import org.KarolSzymaniak.repository.HibernateClientRepository;
import org.KarolSzymaniak.repository.JDBCClientRepository;

import java.util.Scanner;

    public class Main {

        private BankService bankService;

        public static void main(String[] args) {

            new Main().run();
        }




        public void run(){
             //InMemmoryClientRepository repository = new InMemmoryClientRepository(new HashSet<>());
            //ClientRepository repository = new JDBCClientRepository();
            ClientRepository repository = new HibernateClientRepository();
            bankService = new BankService(repository);

            try(Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.println("******************");
                    System.out.println("* 1. - Add user  *");
                    System.out.println("* 2. - find user *");
                    System.out.println("* 3. - Transfer  *");
                    System.out.println("* 4. - exit app  *");
                    System.out.println("******************");
                    String next = scanner.next();
                    if (next.equals("1")){
                        addUser(scanner);
                    }
                    if (next.equals("2")){
                        findUser(scanner);
                    }
                    if (next.equals("3")){
                        System.out.println("Wprowadz mail zleceniodawcy: ");
                        String mail1 = scanner.next();
                        System.out.println("Wprowadz mail zleceniobiorcy: ");
                        String mail2 = scanner.next();
                        System.out.println("Wprowadz kwotę: ");
                        double amountTransfer = scanner.nextDouble();
                        bankService.transfer(mail1,mail2, amountTransfer);
                    }
                    if (next.equals("4")){
                        break;
                    }
                }
            }


        }

        private void findUser(Scanner scanner) {
            System.out.println("Enter email: ");
            String mail = scanner.next();
            System.out.println(bankService.findByEmail(mail));

        }

        private void addUser(Scanner scanner) {
            System.out.println("Enter name: ");
            String name = scanner.next();
            System.out.println("Enter email: ");
            String email = scanner.next();
            System.out.println("Enter balance: ");
            Double balance = scanner.nextDouble();
            bankService.save(new Client(name,email,balance));
        }
    }
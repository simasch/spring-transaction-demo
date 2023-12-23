package ch.martinelli.demo.trx;

import jakarta.persistence.EntityManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrxApplication implements CommandLineRunner {


    private final AccountService accountService;
    private final AccountRepository accountRepository;

    public TrxApplication(AccountService accountService, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TrxApplication.class, args);
    }

    @Override
    public void run(String... args) {
        accountService.createAccounts();

        try {
            accountService.transferMoney(1, 2, 200);
        } catch (RuntimeException ignore) {
        }

        var account1 = accountRepository.findById(1);
        var account2 = accountRepository.findById(2);

        System.out.println(account1.getId() + " " + account1.getBalance());
        System.out.println(account2.getId() + " " + account2.getBalance());

    }
}

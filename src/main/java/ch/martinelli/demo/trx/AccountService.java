package ch.martinelli.demo.trx;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void transferMoney(int from, int to, double amount) {
        accountRepository.updateAccount(to, amount);
        accountRepository.updateAccount(from, -amount);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createAccounts() {
        var account1 = new Account();
        account1.setId(1);
        account1.setBalance(100);
        accountRepository.save(account1);
        var account2 = new Account();
        account2.setId(2);
        account2.setBalance(100);
        accountRepository.save(account2);
    }

}
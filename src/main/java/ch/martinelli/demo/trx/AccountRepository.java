package ch.martinelli.demo.trx;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountRepository {

    private final EntityManager em;

    public AccountRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateAccount(int id, double amount) {
        Account a = em.find(Account.class, id);
        a.setBalance(a.getBalance() + amount);

        if (a.getBalance() < 0) throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Account account) {
        em.persist(account);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Account findById(int id) {
        Account account = em.find(Account.class, id);
        em.refresh(account);
        return account;
    }
}
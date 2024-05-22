import com.example.online_banking_system.model.Account;
import com.example.online_banking_system.repository.AccountRepository;
import com.example.online_banking_system.service.BankingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BankingServiceImplTest {
    @Mock
    private AccountRepository repository;

    @InjectMocks
    private BankingServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDepositMoney() {
        String accountNumber = "12345";
        double amount = 500.0;
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(1000.0);

        when(repository.findByAccountNumber(accountNumber)).thenReturn(account);

        service.depositMoney(accountNumber, amount);

        assertEquals(1500.0, account.getBalance());
        verify(repository, times(1)).save(account);
    }

    @Test
    public void testWithdrawMoney() {
        String accountNumber = "12345";
        double amount = 500.0;
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(1000.0);

        when(repository.findByAccountNumber(accountNumber)).thenReturn(account);

        service.withdrawMoney(accountNumber, amount);

        assertEquals(500.0, account.getBalance());
        verify(repository, times(1)).save(account);
    }







}

package com.example.online_banking_system.service;


import com.example.online_banking_system.model.Account;
import com.example.online_banking_system.repository.AccountRepository;
import com.example.online_banking_system.util.AccountNumberGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

@Service
public class BankingServiceImpl implements BankingService{

    @Autowired
    private AccountRepository repository;

    @Override
    @Transactional
    public void openNewAccount(String accountType, double initialDeposit) {
        Account account = new Account();
        account.setAccountNumber(AccountNumberGenerator.generateAccountNumber());
        account.setBalance(initialDeposit);
        account.setAccountType(accountType);
        repository.save(account);
        System.out.println("Opening a new account of type " + accountType + " with initial deposit of " + initialDeposit);
    }

    @Override
    @Transactional
    @Modifying
    public void depositMoney(String accountNumber, double amount) {
        Account account = repository.findByAccountNumber(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            repository.save(account);
        } else {
            System.out.println("Account number " + accountNumber + " not found");
        }
        System.out.println("Depositing " + amount + " to account number " + accountNumber);
    }

    @Override
    @Transactional
    @Modifying
    public void withdrawMoney(String accountNumber, double amount) {
        Account account = repository.findByAccountNumber(accountNumber);
        if (account != null) {
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                repository.save(account);
            } else {
                System.out.println("Insufficient balance in account number " + accountNumber);
            }
        } else {
            System.out.println("Account number " + accountNumber + " not found");
        }
        System.out.println("Withdrawing " + amount + " from account number " + accountNumber);
    }

    @Override
    @Transactional
    @Modifying
    public void transferMoney(String fromAccount, String toAccount, double amount) {
        withdrawMoney(fromAccount, amount);
        depositMoney(toAccount, amount);


        System.out.println("Transferring " + amount + " from account number " + fromAccount + " to account number " + toAccount);
    }

}

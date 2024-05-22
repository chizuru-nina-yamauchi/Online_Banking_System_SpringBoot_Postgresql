package com.example.online_banking_system.service;


import com.example.online_banking_system.model.Account;
import com.example.online_banking_system.repository.AccountRepository;
import com.example.online_banking_system.util.AccountNumberGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BankingServiceImpl implements BankingService{

    private static final Logger logger = LoggerFactory.getLogger(BankingServiceImpl.class);

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
        logger.info("Opening a new account of type {} with initial deposit of {}", accountType, initialDeposit);
    }

    @Override
    @Transactional
    @Modifying
    public void depositMoney(String accountNumber, double amount) {
        Account account = repository.findByAccountNumber(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            repository.save(account);
            logger.info("Depositing {} to account number {}", amount, accountNumber);
        } else {
            logger.warn("Account number {} not found", accountNumber);
        }

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
                logger.info("Withdrawing {} from account number {}", amount, accountNumber);
            } else {
                logger.warn("Insufficient balance in account number {}", accountNumber);
            }
        } else {
            logger.warn("Account number {} not found", accountNumber);
        }

    }

    @Override
    @Transactional
    @Modifying
    public void transferMoney(String fromAccount, String toAccount, double amount) {
        withdrawMoney(fromAccount, amount);
        depositMoney(toAccount, amount);


        logger.info("Transferring {} from account number {} to account number {}", amount, fromAccount, toAccount);
    }

}

package com.example.online_banking_system.service;

public interface BankingService {
    void openNewAccount(String accountType, double initialDeposit, Long userId);
    void depositMoney(String accountNumber, double amount);
    void withdrawMoney(String accountNumber, double amount);
    void transferMoney(String fromAccount, String toAccount, double amount);
}

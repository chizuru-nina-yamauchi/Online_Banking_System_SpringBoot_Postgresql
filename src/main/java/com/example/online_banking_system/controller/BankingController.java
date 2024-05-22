package com.example.online_banking_system.controller;


import com.example.online_banking_system.model.Account;
import com.example.online_banking_system.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankingController {

    @Autowired
    private BankingService service;

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/accounts/new")
    public String showNewAccountForm(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "new-account";
    }

   @PostMapping("/accounts/new")
    public String openNewAccount(@RequestParam String accountType, @RequestParam double initialDeposit, Model model) {
        service.openNewAccount(accountType, initialDeposit);
        model.addAttribute("message", "New account created successfully.");
        return "redirect:/";
    }

    @GetMapping("/transactions/deposit")
    public String showDepositForm(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "deposit";
    }

    @PostMapping("/transactions/deposit")
    public String depositMoney(@RequestParam String accountNumber, @RequestParam double amount, Model model) {
        service.depositMoney(accountNumber, amount);
        model.addAttribute("message", "Amount deposited successfully.");
        return "redirect:/";
    }

    @GetMapping("/transactions/withdraw")
    public String showWithdrawForm(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "withdraw";
    }

    @PostMapping("/transactions/withdraw")
    public String withdrawMoney(@RequestParam String accountNumber, @RequestParam double amount, Model model) {
        service.withdrawMoney(accountNumber, amount);
        model.addAttribute("message", "Amount withdrawn successfully.");
        return "redirect:/";
    }

    @GetMapping("/transactions/transfer")
    public String showTransferForm(Model model){
        Account account = new Account();
        model.addAttribute("account", account);
        return "transfer";
    }

    @PostMapping("/transactions/transfer")
    public String transferMoney(@RequestParam String fromAccount, @RequestParam String toAccount, @RequestParam double amount, Model model){
        service.transferMoney(fromAccount, toAccount, amount);
        model.addAttribute("message", "Amount transferred successfully.");
        return "redirect:/";
    }






}

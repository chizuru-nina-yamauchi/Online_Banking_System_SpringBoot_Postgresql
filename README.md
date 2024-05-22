### Assignment: Enhance the Online Banking System with Transaction Management

#### Objective:
Extend the functionality of an existing Online Banking System by incorporating advanced transaction management using `@Transactional` and `@Modifying` annotations. This involves creating services for opening new accounts, depositing money, withdrawing money, and transferring funds between accounts. Additionally, update the Thymeleaf templates to allow users to interact with these new functionalities using Bootstrap 5 for styling.

#### Requirements:
1. **Advanced Transaction Management:**
   - Use `@Transactional` to ensure that all financial transactions are processed in a secure and consistent manner.
   - Use `@Modifying` for direct updates in the database for operations like deposits and withdrawals.

2. **Service Layer Updates:**
   - Develop methods that handle the creation of new accounts, deposits, withdrawals, and transfers, ensuring all operations are transactionally secure.

3. **Controller Enhancements:**
   - Introduce new endpoints in the controller to handle requests for each banking operation.

4. **View Modifications:**
   - Modify Thymeleaf templates to include forms for each banking operation.
   - Ensure that the user interface is intuitive and responsive.

#### Tasks:

1. **Implement Transactional Operations:**
   - Create a method to open a new bank account.
   - Implement a method to deposit money into an account.
   - Develop a method for withdrawing money from an account.
   - Set up a method to transfer money between two accounts.

2. **Thymeleaf Template Implementation:**
   - Design forms for opening a new account, depositing money, withdrawing money, and transferring funds.
   - Use Bootstrap 5 to ensure the forms are responsive and user-friendly.

3. **Testing:**
   - Test all functionalities to ensure they work as expected and handle edge cases like overdrafts and transfer errors.

#### Thymeleaf Templates with Bootstrap 5:

### 1. Home Page Template (home.html)

This template serves as the landing page and includes links to other functionalities like account creation, deposits, withdrawals, and transfers.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Online Banking System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Welcome to the Online Banking System</h1>
    <div class="row">
        <div class="col-md-4">
            <a th:href="@{/accounts/new}" class="btn btn-success">Open New Account</a>
        </div>
        <div class="col-md-4">
            <a th:href="@{/transactions/deposit}" class="btn btn-primary">Deposit Money</a>
        </div>
        <div class="col-md-4">
            <a th:href="@{/transactions/withdraw}" class="btn btn-warning">Withdraw Money</a>
        </div>
        <div class="col-md-4 mt-3">
            <a th:href="@{/transactions/transfer}" class="btn btn-info">Transfer Money</a>
        </div>
    </div>
</div>
</body>
</html>
```

### 2. New Account Form Template (new-account.html)

This template includes a form for opening a new bank account.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Open New Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Open a New Account</h1>
    <form th:action="@{/accounts/new}" method="post" class="mt-3">
        <div class="mb-3">
            <label for="accountType" class="form-label">Account Type</label>
            <select id="accountType" name="accountType" class="form-control" required>
                <option value="SAVINGS">Savings</option>
                <option value="CHECKING">Checking</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="initialDeposit" class="form-label">Initial Deposit</label>
            <input type="number" id="initialDeposit" name="initialDeposit" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Open Account</button>
    </form>
</div>
</body>
</html>
```

### 3. Deposit Form Template (deposit.html)

This template includes a form for depositing money into an account.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Deposit Money</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Deposit Money</h1>
    <form th:action="@{/transactions/deposit}" method="post" class="mt-3">
        <div class="mb-3">
            <label for="accountNumber" class="form-label">Account Number</label>
            <input type="text" id="accountNumber" name="accountNumber" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="amount" class="form-label">Amount</label>
            <input type="number" id="amount" name="amount" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Deposit</button>
    </form>
</div>
</body>
</html>
```

### 4. Withdrawal Form Template (withdraw.html)

This template includes a form for withdrawing money from an account.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Withdraw Money</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Withdraw Money</h1>
    <form th:action="@{/transactions/withdraw}" method="post" class="mt-3">
        <div class="mb-3">
            <label for="accountNumber" class="form-label">Account Number</label>
            <input type="text" id="accountNumber" name="accountNumber" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="amount" class="form-label">Amount</label>
            <input type="number" id="amount" name="amount" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Withdraw</button>
    </form>
</div>
</body>
</html>
```

### 5. Transfer Form Template (transfer.html)

This template includes a form for transferring money between two accounts.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Transfer Money</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>Transfer Money</h1>
    <form th:action="@{/transactions/transfer}" method="post" class="mt-3">
        <div class="mb-3">
            <label for="fromAccount" class="form-label">From Account Number</label>
            <input type="text" id="fromAccount" name="fromAccount" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="toAccount" class="form-label">To Account Number</label>
            <input type="text" id="toAccount" name="toAccount" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="amount" class="form-label">Amount</label>
            <input type="number" id="amount" name="amount" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Transfer</button>
    </form>
</div>
</body>
</html>
```

These templates provide a comprehensive user interface for the Online Banking System, allowing users to manage their accounts and perform various transactions efficiently. The use of Bootstrap 5 ensures that the interface is modern and responsive.

public class AccountTest {
    public static void main(String[] args) {
        Account account1 = new Account("A123", 1000.0);
        Account account2 = new Account("B456", 2000.0);
        Account account3 = new Account("C789", 3000.0); // ✅ 新增的第三個帳戶

        System.out.println("帳戶號碼: %s%n初始餘額: %.2f%n".formatted(account1.getAccountNumber(), account1.getBalance()));
        System.out.println("帳戶號碼: %s%n初始餘額: %.2f%n".formatted(account2.getAccountNumber(), account2.getBalance()));
        System.out.println("帳戶號碼: %s%n初始餘額: %.2f%n".formatted(account3.getAccountNumber(), account3.getBalance()));

        account1.deposit(500.0);
        System.out.printf("帳戶號碼: %s%n存款餘額: %.2f%n", account1.getAccountNumber(), account1.getBalance());

        account1.withdraw(1000.0);
        System.out.printf("帳戶號碼: %s%n提款後餘額: %.2f%n", account1.getAccountNumber(), account1.getBalance());

        // 測試多參數存款
        try {
            account1.deposit(100);
            System.out.printf("帳戶號碼: %s 的存款後餘額: %.2f%n",
                    account1.getAccountNumber(),
                    account1.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("存款錯誤訊息: " + e.getMessage());
        }

        // 測試多筆金額存款
        try {
            account2.deposit(1000, 2000, 5000);
            System.out.printf("帳戶號碼: %s 的存款後餘額: %.2f%n",
                    account2.getAccountNumber(),
                    account2.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("存款錯誤訊息: " + e.getMessage());
        }

        // ✅ 測試第三個帳戶存款與提款
        try {
            account3.deposit(1500, 2500);
            account3.withdraw(1000);
            System.out.printf("帳戶號碼: %s 的提款後餘額: %.2f%n",
                    account3.getAccountNumber(),
                    account3.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("帳戶3操作錯誤訊息: " + e.getMessage());
        }

    }
}

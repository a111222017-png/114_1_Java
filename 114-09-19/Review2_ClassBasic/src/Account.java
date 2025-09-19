public class Account {
    // 帳戶號碼
    private String accountNumber;
    // 帳戶餘額
    private double balance;

    /**
     * 建構子：建立帳戶物件
     * @param accountNumber 帳戶號碼
     * @param initialBalance 初始餘額
     */
    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        // 設定初始餘額
        this.balance = initialBalance;
    }

    /**
     * 取得帳戶號碼
     * @return 帳戶號碼
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * 取得帳戶餘額
     * @return 帳戶餘額
     */
    public double getBalance() {
        return balance;
    }

    /**
     * 存款方法
     * @param amount 存入金額
     */
    public void deposit(double amount) {
        // 檢查存款金額是否大於零
        if (amount > 0) {
            balance += amount; // 增加餘額
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive."); // 金額需為正數
        }
    }

    /**
     * 提款方法
     * @param amount 提領金額
     */
    public void withdraw(double amount) {
        // 檢查提款金額是否大於零且不超過餘額
        if (amount > 0 && amount <= balance) {
            balance -= amount; // 減少餘額
            System.out.println("Withdrew: " + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient funds."); // 餘額不足
        } else {
            System.out.println("Withdrawal amount must be positive."); // 金額需為正數
        }
    }
}

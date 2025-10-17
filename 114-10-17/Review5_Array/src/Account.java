public class Account {
    // 帳戶號碼
    private String accountNumber;
    private String ownerName;
    // 帳戶餘額
    private double balance;

    // ✅ 統計建立帳戶數量（可選）
    public static int accountCount = 0;

    /**
     * 建構子：建立帳戶物件（只有帳號與初始餘額）
     * @param accountNumber 帳戶號碼
     * @param initialBalance 初始餘額
     */
    public Account(String accountNumber, double initialBalance) {
        this(accountNumber, "未知", initialBalance); // 呼叫完整版本建構子
    }

    /**
     * ✅ 新增建構子：帳號 + 戶名 + 初始餘額
     * @param accountNumber 帳號
     * @param ownerName 戶名
     * @param initialBalance 初始餘額
     */
    public Account(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;

        try {
            this.setBalance(initialBalance);
        } catch (IllegalArgumentException e) {
            System.out.println("初始餘額錯誤: " + e.getMessage() + "，將餘額設為 0");
            this.balance = 0;
        }
        accountCount++;
    }

    // 無參數建構子
    public Account() {
        this("未知", "未知", 0.0);
    }

    // 只有帳號的建構子
    public Account(String accountNumber) {
        this(accountNumber, "未知", 0.0);
    }

    // 取得帳戶號碼
    public String getAccountNumber() {
        return accountNumber;
    }

    // 取得帳戶餘額
    public double getBalance() {
        return balance;
    }

    // 取得戶名
    public String getOwnerName() {
        return ownerName;
    }

    // 設定帳戶餘額
    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            throw new IllegalArgumentException("帳戶餘額必須為正數");
        }
    }

    // 設定帳戶號碼
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // 存款方法（可變參數）
    public void deposit(double... amounts) {
        double total = 0;
        for (double amount : amounts) {
            if (amount < 0) {
                throw new IllegalArgumentException("存款金額不可為負數");
            }
            total += amount;
        }
        balance += total;
    }

    // 提款方法
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("提款金額必須大於 0");
        } else if (amount > balance) {
            System.out.println("餘額不足，提款失敗");
        } else {
            balance -= amount;
        }
    }
}

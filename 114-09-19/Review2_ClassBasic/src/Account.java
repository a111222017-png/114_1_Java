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
        this.setAccountNumber(accountNumber);
        try {
            this.setBalance(initialBalance);
        }catch (IllegalArgumentException e){
            System.out.println("初始餘額錯誤: "+ e.getMessage() + "，將餘額設為0");
            this.balance = 0; // 初始餘額錯誤時，將餘額設為 0
        }
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

    public void setBalance(double balance) {
        // 檢查傳入的餘額是否大於零，只有正數才允許設定
        if (balance > 0) {
            this.balance = balance; // 將帳戶餘額設為新的值
        } else {
            // 如果餘額不是正數，則丟出例外，提示使用者餘額必須為正數
            throw new IllegalArgumentException("帳戶餘額必須為正數");
        }
    }

    /**
     * 設定帳戶號碼
     * @param accountNumber 欲設定的新帳戶號碼
     */
    public void setAccountNumber(String accountNumber) {
        // 將傳入的帳戶號碼指定給此帳戶物件
        this.accountNumber = accountNumber;
    }


    /**
     * 存款方法
     * @param amount 存入金額
     */

    public void deposit(double amount) {
        // 檢查存款金額是否大於零
        if (amount > 0) {
            balance += amount; // 增加餘額

        } else {
            System.out.println("存款金額必須大於 0"); // 金額需為正數
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

        } else if (amount > balance) {
            System.out.println("餘額不足，提款失敗"); // 餘額不足
        } else {
            System.out.println("存款金額必須大於 0"); // 金額需為正數
        }
    }
}

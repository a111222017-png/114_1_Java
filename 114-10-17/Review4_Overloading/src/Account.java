/*
 * `Account.java`
 * 帳戶類別：提供帳戶號碼、餘額、存款、提款、多筆存款與外幣存款等功能
 * 包含金額驗證（最多三次重試）及錯誤處理（非法初始餘額設為 0）
 * 注意：驗證流程會使用 Scanner 讀取標準輸入，適用於互動式測試或範例用途
 */
import java.util.Scanner;

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

    public Account() {
        this("000000", 0.0);
    }


    public  Account(String accountNumber){
        this.accountNumber = accountNumber;
        this.balance = 0;
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

    private enum AmountType {
        BALANCE, DEPOSIT, WITHDRAW
    }

    /**
     * 驗證金額輸入，最多三次機會
     * @param amount 初始金額
     * @param type 用途類型
     * @param currentBalance 當前餘額（僅提款用）
     * @return 合法金額
     */
    private double validateAmount(double amount, AmountType type, double currentBalance) {
        int attempts = 0;
        Scanner scanner = new Scanner(System.in);
        while (attempts < 3) {
            boolean valid = false;
            switch (type) {
                case BALANCE:
                    valid = amount > 0;
                    break;
                case DEPOSIT:
                    valid = amount > 0;
                    break;
                case WITHDRAW:
                    valid = amount > 0 && amount <= currentBalance;
                    break;
            }
            if (valid) {
                return amount;
            } else {
                attempts++;
                if (attempts < 3) {
                    switch (type) {
                        case BALANCE:
                            System.out.print("餘額必須為正數，請重新輸入：");
                            break;
                        case DEPOSIT:
                            System.out.print("存款金額必須大於 0，請重新輸入：");
                            break;
                        case WITHDRAW:
                            if (amount > currentBalance) {
                                System.out.print("餘額不足，請重新輸入：");
                            } else {
                                System.out.print("提款金額必須大於 0，請重新輸入：");
                            }
                            break;
                    }
                    amount = scanner.nextDouble();
                } else {
                    switch (type) {
                        case BALANCE:
                            throw new IllegalArgumentException("帳戶餘額必須為正數");
                        case DEPOSIT:
                            throw new IllegalArgumentException("存款金額必須大於 0");
                        case WITHDRAW:
                            if (amount > currentBalance) {
                                throw new IllegalArgumentException("餘額不足，提款失敗");
                            } else {
                                throw new IllegalArgumentException("提款金額必須大於 0");
                            }
                    }
                }
            }
        }
        // 不會執行到這裡
        return amount;
    }

    public void setBalance(double balance) {
        this.balance = validateAmount(balance, AmountType.BALANCE, 0);
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
        // 存款方法：驗證傳入金額是否合法後，將有效金額加到帳戶餘額
        // amount - 欲存入的金額（以本幣 TWD 表示）
        double validAmount = validateAmount(amount, AmountType.DEPOSIT, 0); // 驗證存款金額（>0）
        balance += validAmount; // 將驗證後的金額加入餘額
    }

    public void desposit(double amount, String currecy) {
        // 外幣存款方法：將外幣金額依匯率換算成 TWD，然後呼叫 deposit 存入
        // amount - 外幣數量
        // currecy - 貨幣代碼（如 "USD", "EUR", "JPY"），不區分大小寫
        double exchangeRate = 1.0;
        // 根據貨幣代碼設定匯率（此為範例假設匯率）
        switch (currecy.toUpperCase()) {
            case "USD" :
                exchangeRate = 30.0; // 假設 1 USD = 30 TWD
                break;
            case "EUR" :
                exchangeRate = 35.0; // 假設 1 EUR = 35 TWD
                break;
            case "JPY" :
                exchangeRate = 0.25; // 假設 1 JPY = 0.25 TWD
                break;
            default :
                exchangeRate = 1.0; // 預設當作 TWD（或未知貨幣）
                return;
        }
        double amountInTWD = amount * exchangeRate; // 將外幣換算成 TWD
        this.deposit(amountInTWD); // 使用本地存款流程處理換算後的金額
    }

    public void desposit(double ... amounts) {
        // 多筆存款方法：接受可變數量的金額參數，將所有金額合計後一次存入
        // amounts - 一或多筆欲存入的金額（以本幣 TWD 表示）
        double totalAmount = 0.0;
        for (double amount : amounts) {
            totalAmount += amount; // 累加每筆金額
        }
        this.deposit(totalAmount); // 將合計金額送至 deposit 做驗證與入帳
    }

    /**
     * 多重儲值方法，將多個金額一次存入帳戶
     * @param amounts 多個存入金額，皆必須為正數
     */
    public void deposit(double... amounts) {
        double total = 0;
        for (double amount : amounts) {
            if (amount >= 0) {
                total += amount;
            }
            else {
                throw  new IllegalArgumentException("存款金額必須大於 0");
            }
        }
        this.deposit(total);
    }


    /**
     * 提款方法
     * @param amount 提領金額
     */
    public void withdraw(double amount) {
        double validAmount = validateAmount(amount, AmountType.WITHDRAW, balance);
        balance -= validAmount;
    }
}

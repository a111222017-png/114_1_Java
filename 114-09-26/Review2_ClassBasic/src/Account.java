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
        double validAmount = validateAmount(amount, AmountType.DEPOSIT, 0);
        balance += validAmount;
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

import java.time.LocalDateTime;

public class Account {
    private static int accountCount = 0; // 計算帳號數

    private String accountNumber; // 帳號
    private Person owner;          // 擁有者物件
    private String ownerName;      // 擁有者姓名
    private String ownerID;        // 擁有者身分證號
    private double balance;        // 帳戶餘額

    private Date openingDate;      // 開戶日期
    private Time2 openingTime;     // 開戶時間

    /**
     * 帶參數建構子：建立帳戶並初始化資料
     * @param accountNumber 帳號
     * @param ownerName 擁有者姓名
     * @param ownerID 擁有者身分證號
     * @param initialBalance 初始餘額
     */
    public Account(String accountNumber, String ownerName, String ownerID, double initialBalance) {
        LocalDateTime now = LocalDateTime.now(); // 取得當前時間

        this.setAccountNumber(accountNumber);
        this.owner = new Person(ownerName, ownerID);
        this.ownerName = ownerName;
        this.ownerID = ownerID;

        try {
            this.setBalance(initialBalance);
        } catch (IllegalArgumentException e) {
            System.out.println("初始餘額錯誤：" + e.getMessage() + "，已將餘額設為 0");
            this.balance = 0;
        }

        accountCount++; // 帳戶數增加

        // 設定開戶日期與時間
        this.openingDate = new Date(now.getMonthValue(), now.getDayOfMonth(), now.getYear());
        this.openingTime = new Time2(now.getHour(), now.getMinute(), now.getSecond());
    }

    // 只有帳號與初始餘額
    public Account(String accountNumber, double initialBalance) {
        this(accountNumber, "未知", "0000000000", initialBalance);
    }

    // 無參數建構子
    public Account() {
        this("未知", "未知", "0000000000", 0.0);
    }

    // Getter 與 Setter
    public void setAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new IllegalArgumentException("帳號不能為空");
        }
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("餘額不能為負數");
        }
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public String getOwnerID() { return ownerID; }
    public double getBalance() { return balance; }
    public Date getOpeningDate() { return openingDate; }
    public Time2 getOpeningTime() { return openingTime; }

    public static int getAccountCount() { return accountCount; }

    // 存款方法
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("存款金額必須大於 0！");
        }
        balance += amount;
    }

    // 提款方法
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("提款金額必須大於 0！");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("餘額不足，無法提款！");
        }
        balance -= amount;
    }


    @Override
    public String toString() {
        return String.format(
                "帳號: %s%n姓名: %s%n身分證號: %s%n餘額: %.2f%n開戶日期: %s%n開戶時間: %s%n",
                accountNumber, ownerName, ownerID, balance,
                openingDate.toString(), openingTime.toUniversalString()
        );
    }
}

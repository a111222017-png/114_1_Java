import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AccountTest {

    // 查詢帳號
    public static Account customerInAction(List<Account> customers, String accountNumber) {
        for (Account customer : customers) {
            if (customer.getAccountNumber().equals(accountNumber)) {
                return customer;
            }
        }
        System.out.println("找不到指定的帳戶編號: " + accountNumber);
        return null;
    }

    // 新增客戶
    public static void addCustomer(List<Account> customers, Account newAccount) {
        customers.add(newAccount);
        System.out.println("新增客戶成功！帳號: " + newAccount.getAccountNumber());
    }

    // 刪除客戶
    public static void deleteCustomer(List<Account> customers, String accountNumber) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getAccountNumber().equals(accountNumber)) {
                Account removedAccount = customers.remove(i);
                System.out.println("已刪除客戶: " + removedAccount.getAccountNumber() +
                        " (姓名: " + removedAccount.getOwnerName() +
                        "，身分證號: " + removedAccount.getOwnerID() + ")");
                return;
            }
        }
        System.out.println("找不到指定的帳戶編號: " + accountNumber);
    }

    // 顯示所有客戶
    public static void printCustomerAccounts(List<Account> customers) {
        if (customers.isEmpty()) {
            System.out.println("目前沒有任何客戶資料。");
            return;
        }
        for (Account customer : customers) {
            printCustomerInfo(customer);
        }
    }

    // 顯示單一客戶
    public static void printCustomerInfo(Account account) {
        if (account == null) {
            System.out.println("無法列印客戶資料（帳戶不存在）");
            return;
        }
        System.out.println(account.toString());
    }

    // 主程式
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Account> customers = new ArrayList<>();

        while (true) {
            System.out.println("\n===== 銀行帳戶管理系統 =====");
            System.out.println("1. 新增客戶帳戶");
            System.out.println("2. 查詢指定帳戶資料");
            System.out.println("3. 顯示所有客戶帳戶資料");
            System.out.println("4. 刪除客戶帳戶");
            System.out.println("5. 存款");
            System.out.println("6. 提款");
            System.out.println("7. 離開");
            System.out.print("請選擇操作：");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("輸入格式錯誤，請輸入數字選項。");
                continue;
            }

            switch (choice) {
                case 1: // 新增
                    System.out.print("輸入帳號：");
                    String accNum = scanner.nextLine();
                    System.out.print("輸入持有人姓名：");
                    String ownerName = scanner.nextLine();
                    System.out.print("輸入持有人身分證號：");
                    String ownerID = scanner.nextLine();
                    System.out.print("輸入初始存款金額：");
                    double initialBalance = scanner.nextDouble();
                    scanner.nextLine(); // 清除換行符

                    Account newAccount = new Account(accNum, ownerName, ownerID, initialBalance);
                    addCustomer(customers, newAccount);
                    break;

                case 2: // 查詢
                    System.out.print("輸入要查詢的帳號：");
                    String queryNum = scanner.nextLine();
                    Account acc = customerInAction(customers, queryNum);
                    printCustomerInfo(acc);
                    break;

                case 3: // 顯示全部
                    printCustomerAccounts(customers);
                    break;

                case 4: // 刪除
                    System.out.print("輸入要刪除的帳號：");
                    String delNum = scanner.nextLine();
                    deleteCustomer(customers, delNum);
                    break;

                case 5: // 存款
                    System.out.print("輸入帳號：");
                    String accNumDeposit = scanner.nextLine().trim();
                    Account accDeposit = customerInAction(customers, accNumDeposit);
                    if (accDeposit == null) break;
                    System.out.print("輸入存款金額：");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine().trim());
                        accDeposit.deposit(amount);
                        System.out.println("存款成功。當前餘額：" + accDeposit.getBalance());
                    } catch (IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                    } catch (Exception e) {
                        System.out.println("金額格式錯誤。");
                    }
                    break;

                case 6: // 提款
                    System.out.print("輸入帳號：");
                    String accNumWithdraw = scanner.nextLine().trim();
                    Account accWithdraw = customerInAction(customers, accNumWithdraw);
                    if (accWithdraw == null) break;
                    System.out.print("輸入提款金額：");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine().trim());
                        accWithdraw.withdraw(amount);
                        System.out.println("提款成功。當前餘額：" + accWithdraw.getBalance());
                    } catch (IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                    } catch (Exception e) {
                        System.out.println("金額格式錯誤。");
                    }
                    break;

                case 7: // 離開
                    System.out.println("程式結束，再見！");
                    scanner.close();
                    return;

                default:
                    System.out.println("無效的選項，請重新輸入。");
            }
        }
    }
}

import java.io.*;

public class PrintWriterDemo {
    public static void main(String[] args) {
        // 方法一：直接建立
        try (PrintWriter pw = new PrintWriter("report.txt")) {
            pw.println("=== 成績報告 ===");
            pw.println();
            pw.printf("姓名: %s%n", "王小明");
            pw.printf("成績: %d 分%n", 85);
            pw.printf("平均: %.2f%n", 87.5);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 方法二：包裝 BufferedWriter（有緩衝）
        try (PrintWriter pw = new PrintWriter(
                new BufferedWriter(new FileWriter("report2.txt")))) {
            pw.println("有緩衝的 PrintWriter");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 方法三：自動 flush
        try (PrintWriter pw = new PrintWriter(
                new FileWriter("report3.txt"), true)) {
            pw.println("每次 println 都會自動 flush");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

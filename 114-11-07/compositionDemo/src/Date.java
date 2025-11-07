public class Date {
    private int month; // 月份 1-12
    private int day;   // 日期 1-31
    private int year;  // 年份

    private static final int[] daysPerMonth =
            {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // 建構子：檢查輸入的年月日是否合法
    public Date(int month, int day, int year) {
        // 驗證月份
        if (month <= 0 || month > 12) {
            throw new IllegalArgumentException("月份 (" + month + ") 必須為 1-12");
        }

        // 驗證日期是否在當月的有效範圍
        if (day <= 0 || day > daysPerMonth[month] && !(month == 2 && day == 29)) {
            throw new IllegalArgumentException("日期 (" + day + ") 超出指定月份的天數範圍");
        }

        // 若是 2 月 29 日，檢查是否為閏年
        if (month == 2 && day == 29 && !(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))) {
            throw new IllegalArgumentException("日期 (" + day + ") 超出該年份的有效範圍");
        }

        this.month = month;
        this.day = day;
        this.year = year;
    }

    // 以 月/日/年 形式回傳字串
    @Override
    public String toString() {
        return String.format("%d/%d/%d", month, day, year);
    }
}

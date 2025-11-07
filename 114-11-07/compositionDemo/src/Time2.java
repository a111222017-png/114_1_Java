public class Time2 {
    private int hour;   // 小時 0–23
    private int minute; // 分鐘 0–59
    private int second; // 秒數 0–59

    // 無參數建構子：預設為 00:00:00
    public Time2() {
        this(0, 0, 0);
    }

    // 一個參數：指定小時，其餘預設為 0
    public Time2(int hour) {
        this(hour, 0, 0);
    }

    // 兩個參數：指定小時與分鐘，秒數預設為 0
    public Time2(int hour, int minute) {
        this(hour, minute, 0);
    }

    // 三個參數：指定小時、分鐘、秒數
    public Time2(int hour, int minute, int second) {
        if (hour < 0 || hour >= 24) {
            throw new IllegalArgumentException("小時必須介於 0–23");
        }

        if (minute < 0 || minute >= 60) {
            throw new IllegalArgumentException("分鐘必須介於 0–59");
        }

        if (second < 0 || second >= 60) {
            throw new IllegalArgumentException("秒數必須介於 0–59");
        }

        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    // 複製建構子
    public Time2(Time2 time) {
        this(time.hour, time.minute, time.second);
    }

    // Getter methods
    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    // Setter methods
    public void setTime(int hour, int minute, int second) {
        if (hour < 0 || hour >= 24 || minute < 0 || minute >= 60 || second < 0 || second >= 60) {
            throw new IllegalArgumentException("時間設定錯誤！");
        }
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    // 以 24 小時制格式輸出字串
    public String toUniversalString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    // 以 12 小時制格式輸出字串（AM / PM）
    @Override
    public String toString() {
        return String.format("%d:%02d:%02d %s",
                ((hour == 0 || hour == 12) ? 12 : hour % 12),
                minute, second,
                (hour < 12 ? "AM" : "PM"));
    }
}

public class Person {
    private String name;
    private String id;

    // 建構子：同時指定姓名與身分證號
    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    // 取得姓名
    public String getName() {
        return name;
    }

    // 取得身分證號
    public String getId() {
        return id;
    }

    // 以字串形式顯示物件內容
    @Override
    public String toString() {
        return String.format("姓名: %s, 身分證號: %s", name, id);
    }
}

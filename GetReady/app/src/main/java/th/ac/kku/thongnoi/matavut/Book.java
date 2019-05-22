package th.ac.kku.thongnoi.matavut;

public class Book {
    private String title;
    private String time;
    private String item;

    public Book() {

    }

    public Book(String title, String time, String item) {
        this.title = title;
        this.time = time;
        this.item = item;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}

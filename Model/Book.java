package ProjectOOP.Model;

public class Book {
    private String name;
    private String title;
    private String author;
    private String content;

    public Book(String name, String title, String author, String content) {
        this.name = name;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}

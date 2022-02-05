public class Article {
    private String title;
    private String description;
    private String content;

    public Article(String title, String description, String content) {
        this.title = title;
        this.description = description;
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", title, description, content);
    }

    public String getTitle() {
        return title;
    }
}

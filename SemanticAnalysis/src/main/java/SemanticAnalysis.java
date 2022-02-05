import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

public class SemanticAnalysis {
    private static final String CANADA = "canada";
    private final List<Article> articles;

    public SemanticAnalysis() {
        this.articles = new MongoDBConnector().getFromMongo();
    }

    public List<Semantic> getSemantics() {
        final List<String> articleContent = articles.stream().map(article -> article.toString()).collect(toList());
        return of("Canada", "Moncton", "Toronto").map(word -> getSemantic(word, articleContent)).collect(toList());
    }

    public List<SemanticFrequency> getFrequency() {
        return articles.stream().filter(article -> article.toString().toLowerCase().contains(CANADA)).map(article -> {
            final List<String> words = Arrays.stream(article.toString().split("\\s")).collect(toList());
            final List<String> canada = words.stream().filter(word -> word.equalsIgnoreCase(CANADA)).collect(toList());
            return new SemanticFrequency(article.getTitle(), words.size(), canada.size());
        }).collect(toList());
    }

    private Semantic getSemantic(String word, List<String> articleContent) {
        Double count = 0D;
        for (String s : articleContent) {
            count = s.toLowerCase(Locale.ROOT).contains(word.toLowerCase(Locale.ROOT)) ? count + 1: count;
        }
        return new Semantic(word, count, 601);
    }
}

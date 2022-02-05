import static java.lang.Float.valueOf;

public class SemanticFrequency {
    String articleTitle;
    Integer totalWords;
    Integer frequency;
    Float relativeFrequency;

    public SemanticFrequency(String articleTitle, Integer totalWords, Integer frequency) {
        this.articleTitle = articleTitle;
        this.totalWords = totalWords;
        this.frequency = frequency;
        this.relativeFrequency = valueOf(frequency)/ valueOf(totalWords);
    }

    public Float getRelativeFrequency() {
        return relativeFrequency;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", articleTitle, totalWords, frequency, relativeFrequency);
    }
}

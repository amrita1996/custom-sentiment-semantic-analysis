import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleSentiment {
    private Map<String, Integer> wordFrequencyMap = new HashMap<>();
    private Integer articleId;
    private String articleContent;
    private List<String> negativeMatch;
    private List<String> positiveMatch;
    private Polarity polarity;

    public ArticleSentiment(Integer articleId, String articleContent) {
        this.articleId = articleId;
        this.articleContent = articleContent;
    }

    public void setWordFrequencyMap(Map<String, Integer> wordFrequencyMap) {
        this.wordFrequencyMap = wordFrequencyMap;
    }

    public void setNegativeMatch(List<String> negativeMatch) {
        this.negativeMatch = negativeMatch;
    }

    public void setPositiveMatch(List<String> positiveMatch) {
        this.positiveMatch = positiveMatch;
    }

    public void setPolarity(Polarity polarity) {
        this.polarity = polarity;
    }

    @Override
    public String toString() {
        final String negativeMatch = String.join(" ", this.negativeMatch);
        final String positiveMatch = String.join(" ", this.positiveMatch);
        return String.format("%s,%s,%s,%s,%s", articleId, articleContent, negativeMatch, positiveMatch, polarity);
    }
}

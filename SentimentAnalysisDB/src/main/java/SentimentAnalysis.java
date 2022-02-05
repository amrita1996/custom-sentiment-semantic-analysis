import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class SentimentAnalysis {
    public final static String negativePath = "src/main/resources/negativeWords.txt";
    public final static String positivePath = "src/main/resources/positiveWords.txt";
    public List<String> negativeWords;
    public List<String> positiveWords;
    public Integer negativeWordCount = 0;
    public Integer positiveWordCount = 0;

    public SentimentAnalysis() throws IOException {
        loadWords();
    }

    private void loadWords() throws IOException {
        BufferedReader bufferedReader1 = new BufferedReader(new FileReader(negativePath));
        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(positivePath));

        this.negativeWords = bufferedReader1.lines().collect(toList());
        this.positiveWords = bufferedReader2.lines().collect(toList());

        bufferedReader1.close();
        bufferedReader2.close();
    }

    public List<ArticleSentiment> getSentiments() {
        final List<Article> articles = new MongoDBConnector().getFromMongo();
        return articles.parallelStream()
                .map(article -> getSentiment(article, articles.indexOf(article) + 1))
                .collect(toList());
    }

    private ArticleSentiment getSentiment(Article article, Integer index) {
        this.negativeWordCount = 0;
        this.positiveWordCount = 0;
        ArticleSentiment articleSentiment = new ArticleSentiment(index, article.toString());
        final Map<String, Integer> frequencyMap = getFrequencyMap(article.toString());
        List<String> negative = new ArrayList<>();
        List<String> positive = new ArrayList<>();
        setMatch(frequencyMap, negative, positive);

        articleSentiment.setWordFrequencyMap(frequencyMap);
        articleSentiment.setNegativeMatch(negative);
        articleSentiment.setPositiveMatch(positive);
        articleSentiment.setPolarity(getPolarity());
        return articleSentiment;
    }

    private Polarity getPolarity() {
        if (negativeWordCount.equals(positiveWordCount)) {
            return Polarity.neutral;
        } else {
            if (negativeWordCount > positiveWordCount) return Polarity.negative;
            return Polarity.positive;
        }
    }

    private void setMatch(Map<String, Integer> frequencyMap, List<String> negativeWords, List<String> positiveWords) {
        frequencyMap.keySet().forEach(key -> {
            if (this.negativeWords.contains(key)) {
                negativeWords.add(key);
                negativeWordCount += frequencyMap.get(key);
            } else if (this.positiveWords.contains(key)) {
                positiveWords.add(key);
                positiveWordCount += frequencyMap.get(key);
            }
        });
    }

    private Map<String, Integer> getFrequencyMap(String string) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        Arrays.stream(string.split("\\s")).filter(word -> word.matches("[A-z]+")).forEach(s -> {
            if (!frequencyMap.containsKey(s)) {
                frequencyMap.put(s, 0);
            }
            final Integer oldFreq = frequencyMap.get(s);
            frequencyMap.replace(s, oldFreq, oldFreq + 1);
        });
        return frequencyMap;
    }
}

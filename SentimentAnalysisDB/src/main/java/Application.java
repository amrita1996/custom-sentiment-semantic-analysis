import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class Application {
    public static void main(String[] args) throws IOException {
        SentimentAnalysis sentimentAnalysis =  new SentimentAnalysis();
        final List<ArticleSentiment> sentiments = sentimentAnalysis.getSentiments();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/output.csv"));
        bufferedWriter.write(sentiments.stream().map(ArticleSentiment::toString).collect(joining("\n")));
        bufferedWriter.close();
    }
}

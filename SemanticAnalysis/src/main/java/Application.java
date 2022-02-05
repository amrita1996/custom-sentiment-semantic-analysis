import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;

public class Application {
    public static void main(String[] args) throws IOException {
        SemanticAnalysis semantic = new SemanticAnalysis();
        final List<Semantic> semantics = semantic.getSemantics();
        final List<SemanticFrequency> semanticFrequencies = semantic.getFrequency();
        final SemanticFrequency semanticFrequency = semanticFrequencies.stream().max(comparing(SemanticFrequency::getRelativeFrequency)).get();
        System.out.println(semantics);
        System.out.println(semanticFrequency);
        BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter("assignment-4/part-c/SemanticAnalysis/src/main/resources/output_semantics.csv"));
        bufferedWriter1.write(semantics.stream().map(Semantic::toString).collect(joining("\n")));
        bufferedWriter1.close();

        BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter("assignment-4/part-c/SemanticAnalysis/src/main/resources/output_frequency.csv"));
        bufferedWriter2.write(semanticFrequency.toString());
        bufferedWriter2.close();
    }
}

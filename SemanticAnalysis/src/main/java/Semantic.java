public class  Semantic {
    private String searchQuery;
    private Double noOfDocuments;
    private Double fractionOfDocuments;
    private Double logValue;

    public Semantic(String searchQuery, Double noOfDocuments, int totalNumberOfDocuments) {
        this.searchQuery = searchQuery;
        this.noOfDocuments = noOfDocuments;
        this.fractionOfDocuments = totalNumberOfDocuments/noOfDocuments;
        this.logValue = Math.log(fractionOfDocuments);
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s", searchQuery, noOfDocuments, fractionOfDocuments, logValue);
    }
}

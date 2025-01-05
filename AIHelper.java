public class AIHelper {
    private Environment environment;

    public AIHelper(Environment environment) {
        this.environment = environment;
    }

    public void interactiveHelper() {
        System.out.println("\n\033[1;36m--- AI Suggestions 🤖---\033[0m");

        if ("dba".equalsIgnoreCase(environment.getRole())) {
            System.out.println("\033[1;32mSuggestion:\033[0m Choose SQL for structured data and NoSQL for large-scale unstructured data.");
        } else if ("data engineer".equalsIgnoreCase(environment.getRole())) {
            System.out.println("\033[1;32mSuggestion:\033[0m Apache Spark works well for distributed batch processing. Use Talend for ETL pipelines.");
        } else if ("full stack developer".equalsIgnoreCase(environment.getRole())) {
            System.out.println("\033[1;32mSuggestion:\033[0m Use Node.js for faster I/O, Django for robust applications, or Spring Boot for enterprise solutions.");
        }

        System.out.println("\033[1;36mRecommendation:\033[0m For data masking, 'Medium' level is a good balance of security and visibility.");
        System.out.println("\033[1;36mRecommendation:\033[0m Start with 2-3 rows for data subsetting during testing.");
    }
}

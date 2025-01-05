import java.util.Scanner;

public class Environment {
    private String role;
    private String databaseType;
    private String backendTool;
    private String etlOption;
    private String etlProcessor;
    private int subsetRows;
    private String maskingLevel;
    private boolean apiConnectivityEnabled;

    public Environment(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    // Prompt the user to configure options based on the role
    public void configure() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\033[1;34mConfiguring environment for role: " + role + "\033[0m");

        switch (role.toLowerCase()) {  // Convert role input to lowercase for case-insensitive comparison
            case "dba":
                databaseType = getValidInput("Enter Database Type (SQL/NoSQL): ", new String[]{"SQL", "NoSQL"});
                break;

            case "data engineer":
                etlOption = getValidInput("Select ETL Option (Apache Spark/Talend/NiFi): ", new String[]{"Apache Spark", "Talend", "NiFi"});
                etlProcessor = getValidInput("Select ETL Processor (Batch/Real-Time): ", new String[]{"Batch", "Real-Time"});
                break;

            case "full stack developer":
                backendTool = getValidInput("Enter Backend Tool (Spring Boot/Django/Node.js): ", new String[]{"Spring Boot", "Django", "Node.js"});
                String apiChoice = getValidInput("Enable API Connectivity? (yes/no): ", new String[]{"yes", "no"});
                apiConnectivityEnabled = apiChoice.equalsIgnoreCase("yes");
                break;

            default:
                System.out.println("\033[1;31mInvalid role selected. Exiting...\033[0m");
                System.exit(0); // Exit immediately if the role is invalid
                break;
        }

        // Restrict data subset to between 0 and 10
        subsetRows = getValidIntInput("Enter the number of rows for data subset (0-10): ", 0, 10);

        // Restrict masking level to specific options
        maskingLevel = getValidInput("Enter masking level (Low/Medium/High): ", new String[]{"Low", "Medium", "High"});
    }

    // Helper method to get valid input for String options
    private String getValidInput(String prompt, String[] validOptions) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        int attempts = 0;
        boolean isValid = false;

        while (!isValid && attempts < 3) {
            System.out.print("\033[1;33m" + prompt + "\033[0m");
            input = scanner.nextLine().trim();
            for (String option : validOptions) {
                if (input.equalsIgnoreCase(option)) {
                    isValid = true;
                    break;
                }
            }
            if (!isValid) {
                attempts++;
                if (attempts < 3) {
                    System.out.println("\033[1;31mInvalid input. Please select a valid option. Attempt " + (attempts + 1) + "/3.\033[0m");
                }
            }
        }

        if (!isValid) {
            System.out.println("\033[1;31mInvalid input entered 3 times. Exiting...\033[0m");
            System.exit(0); // Exit after 3 failed attempts
        }
        return input;
    }

    // Helper method to get valid integer input within a range
    private int getValidIntInput(String prompt, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int input = -1;
        int attempts = 0;
        boolean isValid = false;

        while (!isValid && attempts < 3) {
            System.out.print("\033[1;35m" + prompt + "\033[0m");
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    isValid = true;
                } else {
                    System.out.println("\033[1;31mInput must be between " + min + " and " + max + ". Attempt " + (attempts + 1) + "/3.\033[0m");
                }
            } catch (NumberFormatException e) {
                System.out.println("\033[1;31mInvalid number format. Please enter a valid number. Attempt " + (attempts + 1) + "/3.\033[0m");
            }
            attempts++;
        }

        if (!isValid) {
            System.out.println("\033[1;31mInvalid input entered 3 times. Exiting...\033[0m");
            System.exit(0); // Exit after 3 failed attempts
        }
        return input;
    }

    // Display summary of environment configuration
    public void displaySummary() {
        System.out.println("\n\033[1;36m--- Environment Configuration Summary ---\033[0m");
        System.out.println("Role: " + role);

        if ("dba".equalsIgnoreCase(role)) {
            System.out.println("\033[1;32mDatabase Type: \033[0m" + databaseType);
        } else if ("data engineer".equalsIgnoreCase(role)) {
            System.out.println("\033[1;32mETL Option: \033[0m" + etlOption);
            System.out.println("\033[1;32mETL Processor: \033[0m" + etlProcessor);
        } else if ("full stack developer".equalsIgnoreCase(role)) {
            System.out.println("\033[1;32mBackend Tool: \033[0m" + backendTool);
            System.out.println("\033[1;32mAPI Connectivity: \033[0m" + (apiConnectivityEnabled ? "Enabled" : "Disabled"));
        }

        System.out.println("\033[1;32mData Subset Rows: \033[0m" + subsetRows);
        System.out.println("\033[1;32mData Masking Level: \033[0m" + maskingLevel);
    }

    // Show demo for Data Masking and Subsetting
    public void showDataDemo() {
        System.out.println("\n\033[1;33m--- Data Subsetting and Masking Demo ---\033[0m");

        // Sample Data for 10 people
        String[][] data = {
                {"1", "John Doe", "john.doe@gmail.com", "1234567890"},
                {"2", "Jane Smith", "jane.smith@gmail.com", "9876543210"},
                {"3", "Alice Brown", "alice.brown@gmail.com", "1112223333"},
                {"4", "Bob White", "bob.white@gmail.com", "4445556666"},
                {"5", "Charlie Black", "charlie.black@gmail.com", "5556667777"},
                {"6", "David Green", "david.green@gmail.com", "6667778888"},
                {"7", "Eve Adams", "eve.adams@gmail.com", "7778889999"},
                {"8", "Frank Miller", "frank.miller@gmail.com", "8889990000"},
                {"9", "Grace Lee", "grace.lee@gmail.com", "9990001111"},
                {"10", "Hannah Scott", "hannah.scott@gmail.com", "1110002222"}
        };

        // Display subsetted data
        System.out.println("\n\033[1;36m--- Data Subset (Rows: " + subsetRows + ") ---\033[0m");
        for (int i = 0; i < Math.min(subsetRows, data.length); i++) {
            System.out.printf("\033[1;37m%s | %s | %s | %s\n\033[0m", data[i][0], data[i][1], data[i][2], data[i][3]);
        }

        // Apply masking
        System.out.println("\n\033[1;35m--- Data Masking (" + maskingLevel + " Level) ---[0m");
        for (int i = 0; i < Math.min(subsetRows, data.length); i++) {
            String maskedEmail = maskEmail(data[i][2], maskingLevel);
            String maskedPhone = maskPhone(data[i][3], maskingLevel);
            System.out.printf("\033[1;37m%s | %s | %s | %s\n\033[0m", data[i][0], data[i][1], maskedEmail, maskedPhone);
        }
    }

    // Helper to mask email
    private String maskEmail(String email, String level) {
        if ("low".equalsIgnoreCase(level)) {
            return email.replaceAll("(?<=.{3}).(?=.*@)", "*");
        } else if ("medium".equalsIgnoreCase(level)) {
            return email.replaceAll("@.*", "@****.com");
        } else {
            return "************";
        }
    }

    // Helper to mask phone
    private String maskPhone(String phone, String level) {
        if ("low".equalsIgnoreCase(level)) {
            return phone.replaceAll("(?<=\\d{6})\\d", "*");
        } else if ("medium".equalsIgnoreCase(level)) {
            return phone.replaceAll("(?<=\\d{2})\\d(?=\\d{2})", "*");
        } else {
            return "**********";
        }
    }
}

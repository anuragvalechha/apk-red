import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private String role;
    private boolean authenticated;

    // Method for login process with colored messages
    public void login() {
        Scanner scanner = new Scanner(System.in);

        // Yellow color for prompts
        System.out.print("\033[1;33mEnter Username: \033[0m");
        username = scanner.nextLine();

        System.out.print("\033[1;33mEnter Password: \033[0m");
        password = scanner.nextLine();

        // Assume valid username and password for testing
        String validUsername = "admin";
        String validPassword = "password123";

        // Check if username and password match the predefined ones
        if (username.equals(validUsername) && password.equals(validPassword)) {
            authenticated = true; // Successful login
            System.out.println("\033[1;32mLogin successful.\033[0m");
        } else {
            authenticated = false; // Failed login
            System.out.println("\033[1;31mInvalid username or password. Exiting...\033[0m");
            // Optionally, allow the user to proceed or retry
            // In this case, we proceed and show that authentication failed but the program continues
        }
    }

    // Method to select role with colored prompts and validation
    public void selectRole() {
        if (!authenticated) {
            // If not authenticated, skip role selection or prompt for a valid login
            System.out.println("\033[1;33mSkipping role selection due to failed authentication.\033[0m");
            this.role = "guest";  // Optionally assign a default role if authentication fails
            return;  // Proceed with default or guest role
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\033[1;33mSelect your role (DBA👷, Data Engineer👨🏻‍💼, Full Stack Developer👨🏻‍💻): \033[0m");
        role = scanner.nextLine().trim().toLowerCase();  // Normalize role input to lowercase

        // Role validation with retry attempts
        int attempts = 0;
        while (attempts < 3) {
            if (role.equals("dba") || role.equals("data engineer") || role.equals("full stack developer")) {
                break;  // Valid role selected
            } else {
                attempts++;
                if (attempts < 3) {
                    System.out.println("\033[1;31mInvalid role. Please select a valid role. Attempt " + (attempts + 1) + "/3.\033[0m");
                    System.out.print("\033[1;33mSelect your role (DBA, Data Engineer, Full Stack Developer): \033[0m");
                    role = scanner.nextLine().trim().toLowerCase();
                }
            }
        }

        // Exit if all attempts fail
        if (attempts == 3) {
            System.out.println("\033[1;31mInvalid role entered 3 times. Exiting...\033[0m");
            System.exit(0);  // Exit program after 3 failed attempts
        }
    }

    // Getter for role
    public String getRole() {
        return role;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Method to check if user is authenticated
    public boolean isAuthenticated() {
        return authenticated;
    }
}

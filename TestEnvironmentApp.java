public class TestEnvironmentApp {
    public static void main(String[] args) {
        System.out.println("\033[1;31m"); // Red color
        System.out.println(" ╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println(" ║                                                                   ║");
        System.out.println(" ║   ██████╗  ███████╗███████╗  ██████╗   █████╗ ████████╗███████╗   ║");
        System.out.println(" ║   ██   ██║ ██╔════╝██   ██║  ██╔════╝ ██╔══██╗╚══██╔══╝██╔════╝   ║");
        System.out.println(" ║   ██████║  █████╗  ██   ██║ ██║  ███╗ ███████║   ██║   █████╗     ║");
        System.out.println(" ║   ██   ██║ ██╔══╝  ██   ██║ ██║   ██║ ██╔══██║   ██║   ██╔══╝     ║");
        System.out.println(" ║   ██║   ███║███████╗██████║  ╚██████╔╝██║  ██║   ██║   ███████╗   ║");
        System.out.println(" ║   ╚═╝      ╚═══════╝╚═════╝   ╚═════╝ ╚═╝  ╚═╝     ╚══════════╝   ║");
        System.out.println(" ║                                                                   ║");
        System.out.println(" ╚═══════════════════════════════════════════════════════════════════╝");
        System.out.println("\033[0m"); // Reset color
        System.out.println(":----------------------------------:\n");
        System.out.println("\033[1;36mWelcome to Test Environment Provisioning System\033[0m");
        System.out.println(": Software author: Anurag valechha 2315819 ,team golden duck 👨🏻‍💻 :\n");
        System.out.println(":----------------------------------:\n");

        User user = new User();
        user.login();

        if (user.isAuthenticated()) {
            System.out.println("\033[1;34mWelcome, " + user.getUsername() + "\033[0m");
            user.selectRole(); // Allow the user to select a valid role

            // Normalize role input (make lowercase and trim spaces)
            String role = user.getRole().trim().toLowerCase();

            // Only proceed if the role is valid
            if (role.equals("dba") || role.equals("data engineer") || role.equals("full stack developer")) {
                Environment environment = new Environment(role);
                environment.configure();
                environment.displaySummary();

                // Show data demo
                environment.showDataDemo();

                // Show AI Suggestions
                AIHelper aiHelper = new AIHelper(environment);
                aiHelper.interactiveHelper();
            } else {
                System.out.println("\033[1;31mInvalid role selected. Exiting...\033[0m");
                System.exit(0); // Exit immediately if the role is invalid
            }
        } else {
            System.out.println("\033[1;31mLogin failed. Exiting the system...\033[0m");
        }
    }
}

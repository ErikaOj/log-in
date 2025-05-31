package aut.ap;

import aut.ap.model.User;
import aut.ap.service.AuthService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        AuthService authService = new AuthService();

        System.out.print("[L]ogin, [S]ign up: ");
        String choice = scn.nextLine().trim().toLowerCase();

        if (choice.equals("s") || choice.equals("sign up")) {
            System.out.print("First Name: ");
            String firstName = scn.nextLine();
            System.out.print("Last Name: ");
            String lastName = scn.nextLine();
            System.out.print("Age: ");
            int age = Integer.parseInt(scn.nextLine());
            System.out.print("Email: ");
            String email = scn.nextLine();
            System.out.print("Password: ");
            String password = scn.nextLine();

            User newUser = new User(firstName, lastName, age, email, password);
            authService.register(newUser);

        } else if (choice.equals("l") || choice.equals("login")) {
            System.out.print("Email: ");
            String email = scn.nextLine();
            System.out.print("Password: ");
            String password = scn.nextLine();

            User user = authService.login(email, password);
            if (user != null) {
                System.out.println("Welcome, " + user.getFirstName() + " " + user.getLastName() + "!");
            } else {
                System.out.println("Invalid email or password.");
            }

        } else {
            System.out.println("Invalid input.");
        }

        scn.close();
    }
}

package registration;

import data.UserShell;

import java.util.Scanner;

public class Registration {
    private String login;
    private String password;
    private UserShell userShell;

    public void registration(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите логин для нового пользователя");
        System.out.print("$");
        this.login = scanner.nextLine();
        System.out.println("Придумайте пароль");
        System.out.print("$");
        String firstPassword = scanner.nextLine();
        System.out.println("Повторите пароль");
        System.out.print("$");
        String secondPassword = scanner.nextLine();
        if (!firstPassword.equals(secondPassword)) {
            System.out.println("Введенные пароли не совпадают. Повторите попытку");
            this.registration();
        } else {
            this.password = Hash.encryptThisString(firstPassword);
            userShell = new UserShell(this.login, this.password, false);
        }
    }

    public void authorization(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите логин или введите registration, чтобы зарегистрироваться");
        System.out.print("$");
        String login = scanner.nextLine();
        if (login.equals("registration")) {
            this.registration();
        } else {
            this.login = login;
            System.out.println("Введите пароль");
            System.out.print("$");
            String newPassword = scanner.nextLine();
            this.password = Hash.encryptThisString(newPassword);
            userShell = new UserShell(this.login, this.password, true);
        }
    }

    public UserShell getUserShell() {
        return userShell;
    }
}

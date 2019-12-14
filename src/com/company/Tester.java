package com.company;

import java.awt.desktop.QuitEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {
    static ArrayList<User> users = new ArrayList<User>();
    static ArrayList<Test> tests = new ArrayList<Test>();
    static User currentUser;
    static private void addUser(String name, String login, String password)
    {
        users.add(new TestedUser(name, login, password));
    }

    static private int findUser(String login, String password)
    {
        for (int i = 0;i<users.size();i++)
            if(users.get(i).enter(login, password))
                return i;
        throw new IllegalArgumentException("Такого пользователя нет, попробуйте снова");
    }

    static private ArrayList getResults()
    {
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < users.size(); i++)
            res.add(users.get(i).getSuccess());
        return res;
    }

    public static void main(String[] args) throws  IllegalArgumentException {
        users.add(new Admin("admin", "123", "qwe"));
        Scanner inp = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("1 - авторизоваться");
            System.out.println("2 - зарегистрироваться");
            System.out.println("3 - выйти");
            int choice = inp.nextInt();
            switch (choice) {
                case 1: {

                    boolean authorization_exit = false;
                    while (!authorization_exit) {
                        System.out.println("Вернуться? (1 - да | 2 - нет)");
                        choice = inp.nextInt();
                        if(choice == 1) break;
                        inp.nextLine();
                        String login = inp.nextLine();
                        String password = inp.nextLine();
                        try {
                            int user_index = findUser(login, password);
                            if (users.get(user_index).isAdmin()) adminInterface(inp);
                            else testedUserInterface(inp, user_index);
                            authorization_exit = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e);
                        }
                    }
                    break;
                }
                case 2:{
                    System.out.println("Введите Имя");
                    inp.nextLine();
                    String name = inp.nextLine();
                    System.out.println("Введите Логин");
                    String login = inp.nextLine();
                    System.out.println("Введите Пароль");
                    String password = inp.nextLine();
                    addUser(name, login, password);
                    break;
                }
                case 3:
                {
                    exit = true;
                    break;
                }
            }
        }
    }
    static private void adminInterface(Scanner inp) {
        boolean exit = false;
        while (!exit) {
            System.out.println("1 - добавить тест");
            System.out.println("2 - добавить вопрос в тест");
            System.out.println("3 - посмотреть результаты");
            System.out.println("4 - вернуться");
            int choice = inp.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Введите название теста");
                    inp.nextLine();
                    String name = inp.nextLine();
                    tests.add(new Test(name));
                    break;
                }
                case 2: {
                    System.out.println("Выберите тест");
                    for (int i = 1;i<=tests.size();i++) System.out.println(i + " - " + tests.get(i-1).getName());
                    int nb = inp.nextInt();
                    while (nb<1 || nb>tests.size())
                    {
                        System.out.println("Введите верное число");
                        nb = inp.nextInt();
                    }
                    nb--;
                    System.out.println("Введите вопрос");
                    inp.next();
                    String text = inp.nextLine();
                    System.out.println("Введите 4 варианта ответа");
                    String answers[] = new String[4];
                    for (int i = 0; i < 4; i++) answers[i] = inp.nextLine();
                    System.out.println("Введите номер правильного ответа (от 1 до 4)");
                    int right = inp.nextInt();
                    tests.get(nb).addQuestion(new Question(text, answers, right));

                    break;
                }
                case 3:
                    System.out.println(String.join("\n", getResults()));
                    break;
                case 4:
                    exit = true;
                    break;
            }
        }
    }
    static private void testedUserInterface(Scanner inp, int user_index)
    {
        currentUser = users.get(user_index);
        boolean exit = false;
        while (!exit)
        {
            System.out.println("Выберите тест");
            for (int i = 1;i<=tests.size();i++) System.out.println(i + " - " + tests.get(i-1).getName());
            int choice = inp.nextInt();
            while (choice<1 || choice>tests.size())
            {
                System.out.println("Введите верное число");
                choice = inp.nextInt();
            }
            ArrayList<Question> questions = tests.get(choice-1).getQuestions();
            for (int i = 0; i<questions.size(); i++)
            {
                System.out.println(questions.get(i).getText() + "\n");
                System.out.println(String.join("\n", questions.get(i).getAnswers()));
                int answer = inp.nextInt();
                currentUser.getAnswer(questions.get(i), answer);
            }
            System.out.println("Выбрать тест снова? (1 - да | 2 - нет)");
            choice = inp.nextInt();
            while (choice<1 || choice>2)
            {
                System.out.println("Введите верное число");
                choice = inp.nextInt();
            }
            if(choice == 2) exit = true;
        }
    }
}
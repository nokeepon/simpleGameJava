import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите значение Ai: ");
        int Ai = scanner.nextInt();
        int maxNumber;

        if (Ai == 0) {
            System.out.print("Вам нужно угадать загаданное число, нижняя граница 1. Напишите верхнюю границу числа: ");
            maxNumber = scanner.nextInt();
        } else {
            maxNumber = Tools.getRandom(1, 1000000);
        }

        Game game = new Game(maxNumber);
        AiPlayer aiPlayer = new AiPlayer(maxNumber);

        while (true) {
            System.out.print("Введите число: ");

            if (Ai == 0) {
                int userNumber = scanner.nextInt();

                int result = game.guess(userNumber);

                if (result == -1) {
                    System.out.println("Больше!");
                } else if (result == 1) {
                    System.out.println("Меньше!");
                } else {
                    System.out.println("Угадал за " + game.getAttempts() + " попыток!");
                    break;
                }
            } else {
                int userNumber = aiPlayer.getUserNumberAi();

                int result = game.guess(userNumber);

                if (result == -1) {
                    System.out.println(userNumber);
                    System.out.println("Больше!");
                    aiPlayer.update(result);
                } else if (result == 1) {
                    System.out.println(userNumber);
                    System.out.println("Меньше!");
                    aiPlayer.update(result);
                } else {
                    System.out.println("Угадал за " + game.getAttempts() + " попыток!");
                    break;
                }
            }
        }

        scanner.close();
    }
}

class Tools {
    public static int getRandom(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
}

class Game {
    private int maxNumber;
    private int attempts = 0;
    private int secret;

    public Game(int maxNumber) {
        this.secret = Tools.getRandom(1, maxNumber);
        this.attempts = 0;
        this.maxNumber = maxNumber;
    }

    public int getAttempts() {
        return attempts;
    }

    public int guess(int userNumber) {
        attempts++;

        if (userNumber < secret) {
            return -1;
        } else if (userNumber > secret) {
            return 1;
        } else {
            return 0;
        }
    }
}

class AiPlayer {
    private int low = 1;
    private int high;
    private int lastGuess;

    public AiPlayer(int maxNumber) {
        this.high = maxNumber;
    }

    public int getUserNumberAi() {
        lastGuess = (low + high) / 2;
        return lastGuess;
    }

    public void update(int result) {
        if (result == -1) {
            low = lastGuess + 1;
        } else if (result == 1) {
            high = lastGuess - 1;
        }
    }
}

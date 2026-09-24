import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Вам нужно угадать загаданное число, нижняя граница 1. Напишите верхню границу числа: ");
        int maxNumber = scanner.nextInt();
        
        Game game = new Game(maxNumber);
        
        while (true){
            System.out.print("Введите число: ");
            int userNumber = scanner.nextInt();
            
            int result = game.guess(userNumber);
            
            if (result == -1) {
                 System.out.println("Больше!");
            }   else if (result == 1) {
                 System.out.println("Меньше!");
            }   else  {
                System.out.println("Угадал за " + game.getAttempts() + " попыток!");
                break;
            }
        }
        
        scanner.close();
    }
}


class Tools{
    public static int getRandom(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
}

class Game {
    private int maxNumber; 
    private int attempts = 0;
    private int secret;
    
    public Game(int maxNumber){
        this.secret = Tools.getRandom(1, maxNumber);
        this.attempts = 0;
        this.maxNumber = maxNumber;
    }
    
    public int getAttempts(){
        return attempts;
    }
    
    public int guess(int userNumber){
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
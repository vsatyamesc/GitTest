// Explore multithreading in Java to perform multiple tasks concurrently.

class Task1 extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Task1 - " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Task1 interrupted.");
                break;
            }
        }
    }
}

class Task2 extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Task2 - " + i);
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                System.out.println("Task2 interrupted.");
                break;
            }
        }
    }
}

public class MultiThreading {
    public static void main(String[] args) {
        Task1 t1 = new Task1();
        Task2 t2 = new Task2();

        t1.start();
        t2.start();
    }
}

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        try {
            Thread.sleep(999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

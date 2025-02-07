import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private int choice;
    private Scanner scanner = new Scanner (System.in);
    
    public int int_getChoice (ArrayList <String> options) {
        for (String i: options) {
            System.out.println(i);
        }
        System.out.print("Please choose an option 1..11: ");
        choice = scanner.nextInt();
        return choice;
    }

    public int int_getChoice (BrandList brand) {
        int n = brand.size();
        for (int i = 0; i < n; i++) {
            System.out.println("" + (i+1) + ". " + brand.get(i));
        }
        System.out.print("Please choose an option 1..11: ");
        choice = scanner.nextInt();
        return choice;
    }

    public Brand ref_getChoice (BrandList options) {
        int N = options.size();
        System.out.println("Brand ID List:");
        do {
            choice = int_getChoice(options);
        } while ((choice < 0) || (choice > N));
        return options.get(choice - 1);
    }
}
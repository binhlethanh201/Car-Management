import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BrandList extends ArrayList <Brand> {
    private String brandID;
    private String brandName;
    private String soundBrand;
    private double price;
    private int pos;
    Scanner scanner = new Scanner(System.in);
    PrintWriter pw;
    BufferedReader br;

  
    public boolean loadFromFile(String fileName) throws IOException {
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr.length >= 3) {  
                    brandID = arr[0];
                    brandName = arr[1];
                    soundBrand = arr[2].split(":")[0];
                    String pricee = arr[2].split(":")[1];
                    price = Double.parseDouble(pricee);
                    this.add(new Brand(brandID, brandName, soundBrand, price));
                } 
            }
            br.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found!");
        }
        return false;
}

    public boolean saveToFile (String fileName) {
        try {
            pw = new PrintWriter(new FileWriter(fileName));
            for (Brand i: this) {
                pw.println(i);
            }
            pw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

     public int searchID (String bID) {
        for (int i = 0; i < this.size(); i++) {
            if (bID.equals(this.get(i).getBrandID())) {
                return i;
            }
        }
        return -1;
    }

    public Brand getUserChoice () {
        Menu menu = new Menu();
        return (Brand) menu.ref_getChoice(this);
    }

    public void addBrand () {
        boolean checkBrandID = false;
        do {
            System.out.print("Nhập brand ID: ");
            brandID = scanner.nextLine();
            for (int i = 0; i < this.size(); i++) {
                if (brandID.equals(this.get(i).getBrandID())) {
                    checkBrandID = true;
                    System.out.println("ID bị trùng! Hãy nhập lại");
                    break;
                } else {
                    checkBrandID = false;
                }
            }
        } while (checkBrandID == true);
        do {
            System.out.print("Nhập brand name: ");
            brandName = scanner.nextLine();
            if (brandName.equals("") != true) {
                break;
            }
            System.out.println("Tên không thể rỗng! Hãy nhập lại");
        } while (true);
        do {
            System.out.print("Nhập sound brand: ");
            soundBrand = scanner.nextLine();
            if (soundBrand.equals("") != true) {
                break;
            }
            System.out.println("Sound brand không thể rỗng! Hãy nhập lại");
        } while (true);
        do {
            System.out.print("Nhập price: ");
            try {
                price = new Scanner(System.in).nextDouble();
                if (price <= 0) {
                    System.out.println("Sai điều kiện! Hãy nhập lại");
                }
            } catch (NumberFormatException e) {
                System.out.println("Price phải là 1 sô! Hãy nhập lại");
                price = 0;
            }
        } while (price == 0);
        this.add(new Brand(brandID, brandName, soundBrand, price));
        System.out.println("Brand has added successfully");
    }

    public void updateBrand () {
        do {
            System.out.print("Nhập brand ID: ");
            brandID = scanner.nextLine();
            pos = searchID (brandID);
            if (pos != -1) {
                break;
            }
            System.out.println("Không tìm thấy brandID");
        } while (true);
         do {
            System.out.print("Nhập brand name: ");
            brandName = scanner.nextLine();
            if (brandName.equals("") != true) {
                break;
            }
            System.out.println("Tên không thể rỗng! Hãy nhập lại");
        } while (true);
        do {
            System.out.print("Nhập sound brand: ");
            soundBrand = scanner.nextLine();
            if (soundBrand.equals("") != true) {
                break;
            }
            System.out.println("Sound brand không thể rỗng! Hãy nhập lại");
        } while (true);
        do {
            System.out.print("Nhập price: ");
            try {
                price = new Scanner(System.in).nextDouble();
                if (price <= 0) {
                    System.out.println("Sai điều kiện! Hãy nhập lại");
                }
            } catch (NumberFormatException e) {
                System.out.println("Price phải là 1 sô! Hãy nhập lại");
                price = 0;
            }
        } while (price == 0);
        this.get(0).setUpdatedBrand(brandName, soundBrand, price);
        System.out.println("Brand has updated successfully !");
    }

    
    public void listBrands() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i));
        }
    }
}
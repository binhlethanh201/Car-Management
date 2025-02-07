import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CarList extends ArrayList <Car> {
    private String carID;
    private String color;
    private String frameID;
    private String engineID;
    private Brand brand;
    Menu menu = new Menu();
    Scanner scanner = new Scanner (System.in);
    BrandList brandList;
    BufferedReader br;
    String line;
    String [] arr;
    
    public CarList (BrandList bList) {
        brandList = bList;
    }

    public boolean loadFromFile(String fileName) {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] arr = line.split(",");
            String carID = arr[0];
            String brandID = arr[1];

            // Check if the brandID exists in the BrandList
            int brandIndex = brandList.searchID(brandID);
            if (brandIndex != -1) {
                Brand brand = brandList.get(brandIndex);
                String color = arr[2];
                String frameID = arr[3];
                String engineID = arr[4];
                this.add(new Car(carID, brand, color, frameID, engineID));
            }
        }
        return true;
    } catch (FileNotFoundException e) {
        System.out.println("File " + fileName + " not found!");
    } catch (IOException e) {
        System.out.println("Error reading data from file: " + e.getMessage());
    }
    return false;
}


    public boolean saveToFile (String fileName) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter (fileName));
            for (Car i: this) {
                pw.println(i);
            }
            pw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int searchID (String carID) {
        for (int i = 0; i < this.size(); i++) {
            if (carID.equals(this.get(i).getID())) {
                return i;
            }
        }
        return -1;
    }
    
    private int searchEngineID(String searchEngineID) {
        for (int i = 0; i < this.size(); i++) {
            if (searchEngineID.equals(this.get(i).getEngineID())) {
                return i;
            }
        }
        return -1;
    }

    private int searchFrameID(String searchFrameID) {
        for (int i = 0; i < this.size(); i++) {
            if (searchFrameID.equals(this.get(i).getFrameID())) {
                return i;
            }
        }
        return -1;
    }

    //Add car to the set
    public void addCar () {
        boolean checkCarID = false;
        do {
            System.out.print("Nhập car ID: ");
            carID = scanner.nextLine();
            for (int i = 0; i < this.size(); i++) {
                if (carID.equals(this.get(i).getID())) {
                    checkCarID = true;
                    System.out.println("CarID không tồn tại! Hãy nhập lại");
                    break;
                } else {
                    checkCarID = false;
                }
            }
        } while (checkCarID == true);

        Brand brand = menu.ref_getChoice(brandList);
            
        do {
            System.out.print("Nhập color: ");
            color = scanner.nextLine();
            if (color.equals("") != true) {
                break;
            }
            System.out.println("Color không được rỗng! Hãy nhập lại");
        } while (true);
        do {
            System.out.print("Nhập frame ID: ");
            frameID = scanner.nextLine();
            if ((frameID.matches("F[0-9][0-9][0-9][0-9][0-9]")) && (searchFrameID(frameID) == -1)) {
                break;
            }
            System.out.println("FrameID không có dạng F00000 hoặc bi trùng lặp! Hãy nhập lại");
        } while (true);
        do {
            System.out.print("Nhập engine ID: ");
            engineID = scanner.nextLine();
            if ((engineID.matches("E[0-9][0-9][0-9][0-9][0-9]")) && (searchEngineID(engineID) == -1)) {
                break;
            }
            System.out.println("EngineID không có dạng F00000 hoặc bi trùng lặp! Hãy nhập lại");
        } while (true);
        this.add(new Car(carID, brand, color, frameID, engineID));
        System.out.println("Car has added successfully !");
    }


    public void printBasedBrandName () {
        String aPartOfBrandName;
        int count = 0;
        System.out.println("Nhập brand name: ");
        aPartOfBrandName = scanner.nextLine();
        for (int i = 0; i < this.size(); i++) {
            if (aPartOfBrandName.matches(this.get(i).brand.getBrandName())) {
                System.out.println(this.get(i).screenString());
                count ++;
            }
            if (count == 0) {
                System.out.println("Không phát hiện !");
            }
        }
    }

    public boolean removeCar() {
        int pos;
        String removedID;
        System.out.print("Nhập carID của Car muốn xóa: ");
        removedID = scanner.nextLine();
        pos = searchID(removedID);
        if (pos >= 0) {
            this.remove(pos);
            return true;
        }
        return false;
    }

    public boolean updateCar () {
        int pos;
        String updatedID;
        System.out.print("Nhập carID để update: ");
        updatedID = scanner.nextLine();
        pos = searchID(updatedID);
        if (pos >= 0) {
            Brand brand = menu.ref_getChoice(brandList);
            
            do {
                System.out.print("Nhập color: ");
                color = scanner.nextLine();
                if (color.equals("") != true) {
                    break;
                }
                System.out.println("Color không được rỗng! Hãy nhập lại");
            } while (true);
            do {
                System.out.print("Nhập frame ID: ");
                frameID = scanner.nextLine();
                if ((frameID.matches("F[0-9][0-9][0-9][0-9][0-9]")) && (searchFrameID(frameID) == -1)) {
                    break;
                }
                System.out.println("FrameID không có dạng F00000 hoặc bi trùng lặp! Hãy nhập lại");
            } while (true);
            do {
                System.out.print("Nhập engine ID: ");
                engineID = scanner.nextLine();
                if ((engineID.matches("E[0-9][0-9][0-9][0-9][0-9]")) && (searchEngineID(engineID) == -1)) {
                    break;
                }
                System.out.println("EngineID không có dạng F00000 hoặc bi trùng lặp! Hãy nhập lại");
            } while (true);
            this.get(pos).setUpdatedCar (brand, color, frameID, engineID);
            return true;
        } else {
            System.out.println("Không tồn tại carID!");
        }
        return false;
    }

    //Listing cars in ascending order of brand names
    public void listCars () {
        Collections.sort(this);
        for (Car i: this) {
            System.out.println(i.toString());
        }
    }
}
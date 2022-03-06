package app;

import dao.PatientDao;
import dao.PatientDaoImpl;
import pojo.Patient;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class PatientApplication {

    public static void main(String[] args) {
        PatientDao patientD = new PatientDaoImpl();
        Patient patient = new Patient();
        List<Patient> patientList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to simple CRUD application!");
        int choice = -1;
        do {
            printMenu();
            System.out.print("\nEnter an option: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    try {
                        patient = inputWithoutId();
                        patientD.save(patient);
                    } catch (Exception e) {
                        System.out.println("Try again");
                    }
                    break;
                case 2:
                    System.out.print("\nEnter id: ");
                    try {
                        System.out.println(patientD.get(scanner.nextInt()));
                    } catch (Exception e) {
                        scanner.nextLine();
                        System.out.println("Try again");
                    }
                    break;
                case 3:
                    patientList = patientD.getAll();
                    for (Patient pat: patientList) {
                        System.out.println(pat);
                    }
                    break;
                case 4:
                    try {
                        patient = input();
                        patientD.update(patient);
                    } catch (Exception e) {
                        System.out.println("Try again");
                    }
                    break;
                case 5:
                    System.out.print("\nEnter name that you want to change in DB: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.print("\nEnter desired name: ");
                    String desiredName = scanner.nextLine();
                    patientD.updateByName(name, desiredName);
                    break;
                case 6:
                    System.out.print("\nEnter id: ");
                    try {
                        patientD.delete(scanner.nextInt());
                    } catch (Exception e) {
                        System.out.println("Try again");
                    }
                    break;
                case 7:
                    System.out.print("\nEnter name that you want to delete in DB: ");
                    scanner.nextLine();
                    try {
                        patientD.deleteByName(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Try again");
                    }
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("\nEnter the data again!");
            }
        } while (choice != 8);
    }

    public static void printMenu() {
        System.out.println("\n1. Create a field and insert it into database ");
        System.out.println("2. Read a field of certain id from database");
        System.out.println("3. Read all fields from database(printing fields to console)");
        System.out.println("4. Update a whole field at a certain id");
        System.out.println("5. Update fields with certain name by desired name");
        System.out.println("6. Delete a field at a certain id");
        System.out.println("7. Delete fields with certain name");
        System.out.println("8. Exit\n");
    }

    public static Patient inputWithoutId() {
        Scanner scanner = new Scanner(System.in);
        Patient patient = new Patient();
        System.out.print("\nEnter a name of the patient: ");
        patient.setName(scanner.nextLine());
        System.out.print("\nEnter an age of the patient: ");
        patient.setAge(scanner.nextInt());
        System.out.print("\nDoes the patient has HIV?: ");
        patient.setHivInfected(scanner.nextBoolean());
        return patient;
    }

    public static Patient input() {
        Scanner scanner = new Scanner(System.in);
        Patient patient = new Patient();
        System.out.print("Enter an id of the patient: ");
        patient.setId(scanner.nextInt());
        System.out.print("\nEnter a name of the patient: ");
        scanner.nextLine();
        patient.setName(scanner.nextLine());
        System.out.print("\nEnter an age of the patient: ");
        patient.setAge(scanner.nextInt());
        System.out.print("\nDoes the patient has HIV?: ");
        patient.setHivInfected(scanner.nextBoolean());
        return patient;
    }
}

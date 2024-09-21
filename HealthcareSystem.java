import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    String username;
    String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class Patient extends User {
    ArrayList<String> medicalRecords;

    Patient(String username, String password) {
        super(username, password);
        this.medicalRecords = new ArrayList<>();
    }

    void addMedicalRecord(String record) {
        medicalRecords.add(record);
    }
}

class Doctor extends User {
    ArrayList<String> appointments;

    Doctor(String username, String password) {
        super(username, password);
        this.appointments = new ArrayList<>();
    }

    void addAppointment(String appointment) {
        appointments.add(appointment);
    }
}

public class HealthcareSystem {
    static Map<String, Patient> patients = new HashMap<>();
    static Map<String, Doctor> doctors = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Patient Registration");
            System.out.println("2. Doctor Registration");
            System.out.println("3. Patient Login");
            System.out.println("4. Doctor Login");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    registerPatient(scanner);
                    break;
                case 2:
                    registerDoctor(scanner);
                    break;
                case 3:
                    patientLogin(scanner);
                    break;
                case 4:
                    doctorLogin(scanner);
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }

    private static void registerPatient(Scanner scanner) {
        System.out.println("Enter Patient Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();
        patients.put(username, new Patient(username, password));
        System.out.println("Patient registered successfully!");
    }

    private static void registerDoctor(Scanner scanner) {
        System.out.println("Enter Doctor Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();
        doctors.put(username, new Doctor(username, password));
        System.out.println("Doctor registered successfully!");
    }

    private static void patientLogin(Scanner scanner) {
        System.out.println("Enter Patient Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        Patient patient = patients.get(username);

        if (patient != null && patient.password.equals(password)) {
            patientMenu(scanner, patient);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void doctorLogin(Scanner scanner) {
        System.out.println("Enter Doctor Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();

        Doctor doctor = doctors.get(username);

        if (doctor != null && doctor.password.equals(password)) {
            doctorMenu(scanner, doctor);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void patientMenu(Scanner scanner, Patient patient) {
        while (true) {
            System.out.println("1. View Medical Records");
            System.out.println("2. Add Medical Record");
            System.out.println("3. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    viewMedicalRecords(patient);
                    break;
                case 2:
                    addMedicalRecord(scanner, patient);
                    break;
                case 3:
                    return;
            }
        }
    }

    private static void doctorMenu(Scanner scanner, Doctor doctor) {
        while (true) {
            System.out.println("1. View Appointments");
            System.out.println("2. Add Appointment");
            System.out.println("3. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    viewAppointments(doctor);
                    break;
                case 2:
                    addAppointment(scanner, doctor);
                    break;
                case 3:
                    return;
            }
        }
    }

    private static void viewMedicalRecords(Patient patient) {
        System.out.println("Medical Records:");
        for (String record : patient.medicalRecords) {
            System.out.println(record);
        }
    }

    private static void addMedicalRecord(Scanner scanner, Patient patient) {
        System.out.println("Enter Medical Record:");
        String record = scanner.nextLine();
        patient.addMedicalRecord(record);
        System.out.println("Medical Record added successfully!");
    }

    private static void viewAppointments(Doctor doctor) {
        System.out.println("Appointments:");
        for (String appointment : doctor.appointments) {
            System.out.println(appointment);
        }
    }

    private static void addAppointment(Scanner scanner, Doctor doctor) {
        System.out.println("Enter Appointment:");
        String appointment = scanner.nextLine();
        doctor.addAppointment(appointment);
        System.out.println("Appointment added successfully!");
    }
}
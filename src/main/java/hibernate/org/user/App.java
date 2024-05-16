package hibernate.org.user;

import java.sql.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class App {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
        config.addAnnotatedClass(user.class);

        SessionFactory sf = config.buildSessionFactory();
        Session s = sf.openSession();

        Scanner scanner = new Scanner(System.in);

        try {
            boolean exit = false;
            boolean isAdmin = false;
            user currentUser = null;

            while (!exit) {
                System.out.println("Silahkan pilih:");
                System.out.println("1. Login");
                System.out.println("2. Register");

                int pilihan = 0;
                boolean validInput = false;

                while (!validInput) {
                    try {
                        pilihan = scanner.nextInt();
                        validInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Input harus berupa bilangan bulat (1 atau 2). Silakan coba lagi.");
                        scanner.next();
                    }
                }

                scanner.nextLine();

                if (pilihan == 1) {
                    boolean loggedIn = false;
                    int attempts = 3;

                    while (!loggedIn && attempts > 0) {
                        System.out.println("Masukkan email:");
                        String email = scanner.next();

                        System.out.println("Masukkan kata sandi:");
                        String password = scanner.next();

                        Query<user> query = s.createQuery("FROM user WHERE email = :email", user.class);
                        query.setParameter("email", email);

                        try {
                            currentUser = query.uniqueResult();

                            if (currentUser != null && currentUser.getPassword().equals(password)) {
                                // Login berhasil
                                System.out.println("Login berhasil");
                                loggedIn = true;

                                String firstName = currentUser.getFirstName().toLowerCase();
                                String lastName = currentUser.getLastName().toLowerCase();
                                int id = currentUser.getID();

                                if (firstName.equals("tobiichi") && lastName.equals("origami")
                                        && email.equalsIgnoreCase("TobiichiOrigami@gmail.com") && password.equals("Origami2000") && id == 1) {
                                    isAdmin = true;

                                if (isAdmin) {
                                    System.out.println("Anda masuk sebagai Admin.");
                                    boolean adminExit = false;
                                    while (!adminExit) {
                                        System.out.println("1. View all database");
                                        System.out.println("2. Delete user");
                                        System.out.println("3. Logout");

                                        int adminChoice = scanner.nextInt();
                                        scanner.nextLine(); // consume newline

                                        switch (adminChoice) {
                                            case 1:
                                                viewAllUsers(s);
                                                break;
                                            case 2:
                                                deleteUser(s, scanner);
                                                break;
                                            case 3:
                                                adminExit = true;
                                                System.out.println("Logout berhasil.");
                                                break;
                                            default:
                                                System.out.println("Pilihan tidak valid.");
                                                break;
                                            }
                                        }
                                    }
                                } else {
                                    System.out.println("Anda masuk sebagai Pengguna Biasa.");
                                    boolean userExit = false;
                                    while (!userExit) {
                                        System.out.println("1. Lihat informasi login saat ini");
                                        System.out.println("2. Ubah kata sandi");
                                        System.out.println("3. Logout");

                                        int userChoice = scanner.nextInt();
                                        scanner.nextLine(); // consume newline

                                        switch (userChoice) {
                                            case 1:
                                                displayCurrentUserInfo(currentUser);
                                                break;
                                            case 2:
                                                changePassword(s, scanner, currentUser);
                                                break;
                                            case 3:
                                                userExit = true;
                                                System.out.println("Logout berhasil.");
                                                break;
                                            default:
                                                System.out.println("Pilihan tidak valid.");
                                                break;
                                        }
                                    }
                                }
                            } else {
                                attempts--;
                                if (attempts > 0) {
                                    System.out.println("Login gagal. Silakan coba lagi. Sisa percobaan: " + attempts);
                                } else {
                                    System.out.println("Login gagal. Anda telah mencapai batas maksimal percobaan.");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Terjadi kesalahan saat proses login. Silakan coba lagi nanti.");
                            e.printStackTrace();
                            break;
                        }
                    }

                } else if (pilihan == 2) {
                    createUser(s, scanner);
                }
                System.out.println("Apakah Anda ingin keluar? (y/n)");
                String exitChoice = scanner.next();

                if (exitChoice.equalsIgnoreCase("y")) {
                    exit = true;
                } else {
                    System.out.println("Kembali ke menu...");
                }
            }
        } finally {
            scanner.close();
            s.close();
            sf.close();
        }
    }

    // Create
    private static void createUser(Session session, Scanner scanner) {
        System.out.println("Masukkan id pengguna: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
    
        System.out.println("Masukkan nama depan: ");
        String firstName = scanner.nextLine().toLowerCase();
    
        System.out.println("Masukkan nama belakang: ");
        String lastName = scanner.nextLine().toLowerCase();
    
        System.out.println("Masukkan nomor telepon: ");
        int phoneNumber = scanner.nextInt(); // Change input to int
        scanner.nextLine(); // consume newline
    
        System.out.println("Masukkan alamat Rumah: ");
        String address = scanner.nextLine();
    
        System.out.println("Masukkan tanggal lahir (yyyy-mm-dd): ");
        Date dateOfBirth = Date.valueOf(scanner.nextLine());
    
        System.out.println("Masukkan jenis kelamin (Male/Female): ");
        user.Gender gender = user.Gender.valueOf(scanner.nextLine());
    
        System.out.println("Masukkan email: ");
        String email = scanner.nextLine();
        if (!email.contains("@")) {
            System.out.println("Email tidak valid. Silakan masukkan email yang valid.");
            return;
        }
    
        System.out.println("Masukkan kata sandi: ");
        String password = scanner.nextLine();
    
        System.out.println("Konfirmasi kata sandi: ");
        String confirmPassword = scanner.nextLine();
        if (!password.equals(confirmPassword)) {
            System.out.println("Password dan konfirmasi password tidak cocok. Silakan coba lagi.");
            return;
        }
    
        System.out.println("Masukkan profil: ");
        String profile = scanner.nextLine();
    
        user u = new user(id, firstName, lastName, phoneNumber, address, dateOfBirth, gender, email, password, confirmPassword, profile);
    
        session.beginTransaction();
        session.persist(u);
        session.getTransaction().commit();
        System.out.println("Data tersimpan....");
    }

    // Read
    private static void viewAllUsers(Session session) {
        Query<user> query = session.createQuery("FROM user", user.class);
        List<user> users = query.getResultList();
        for (user u : users) {
            System.out.println("ID: " + u.getID());
            System.out.println("First Name: " + u.getFirstName());
            System.out.println("Last Name: " + u.getLastName());
            System.out.println("Phone Number: " + u.getPhoneNumber());
            System.out.println("Address: " + u.getAddress());
            System.out.println("Date of Birth: " + u.getDateOfBirth());
            System.out.println("Gender: " + u.getGender());
            System.out.println("Email: " + u.getEmail());
            System.out.println("Profile: " + u.getProfile());
            System.out.println("---------------------------");
        }
    }


// Update
private static void changePassword(Session session, Scanner scanner, user currentUser) {
    System.out.println("Masukkan kata sandi lama:");
    String oldPassword = scanner.nextLine();

    if (currentUser.getPassword().equals(oldPassword)) {
        System.out.println("Masukkan kata sandi baru:");
        String newPassword = scanner.nextLine();
        
        System.out.println("Konfirmasi kata sandi baru:");
        String confirmPassword = scanner.nextLine();

        if (newPassword.equals(confirmPassword)) {
            session.beginTransaction();
            currentUser.setPassword(newPassword);
            session.update(currentUser);
            session.getTransaction().commit();
            System.out.println("Kata sandi berhasil diubah.");
        } else {
            System.out.println("Kata sandi baru dan konfirmasi kata sandi tidak cocok.");
        }
    } else {
        System.out.println("Kata sandi lama tidak sesuai.");
    }
}

// Delete
private static void deleteUser(Session session, Scanner scanner) {
    System.out.println("Masukkan ID pengguna yang ingin dihapus:");
    int idToDelete = scanner.nextInt();
    scanner.nextLine(); // consume newline

    user userToDelete = session.get(user.class, idToDelete);
    if (userToDelete != null) {
        session.beginTransaction();
        session.remove(userToDelete);
        session.getTransaction().commit();
        System.out.println("Pengguna dengan ID " + idToDelete + " berhasil dihapus.");
    } else {
        System.out.println("Pengguna dengan ID " + idToDelete + " tidak ditemukan.");
    }
}

// Read for current user info
private static void displayCurrentUserInfo(user currentUser) {
    System.out.println("Informasi Pengguna Saat Ini:");
    System.out.println("ID: " + currentUser.getID());
    System.out.println("Nama Depan: " + currentUser.getFirstName());
    System.out.println("Nama Belakang: " + currentUser.getLastName());
    System.out.println("Nomor Telepon: " + currentUser.getPhoneNumber());
    System.out.println("Alamat: " + currentUser.getAddress());
    System.out.println("Tanggal Lahir: " + currentUser.getDateOfBirth());
    System.out.println("Jenis Kelamin: " + currentUser.getGender());
    System.out.println("Email: " + currentUser.getEmail());
    System.out.println("Profil: " + currentUser.getProfile());
    }
}
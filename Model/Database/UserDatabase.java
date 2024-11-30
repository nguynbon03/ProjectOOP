package ProjectOOP.Model.Database;

import ProjectOOP.Model.User;

import java.io.*;

public class UserDatabase {

    private final String FILE_PATH = "users.txt";

    public UserDatabase() {
        File file = new File(FILE_PATH);
        try {
            // Tạo file nếu chưa tồn tại
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(User user) {
        if (validateUser(user)) {
            return false; // Người dùng đã tồn tại
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(user.getUsername() + "," + user.getPassword());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validateUser(User user) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                        return true; // Tìm thấy người dùng hợp lệ
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Không tìm thấy người dùng
    }

}

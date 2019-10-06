package glim.antony.spring_led_market;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
    public static void main(String[] args) {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String pass = passwordEncoder.encode("100");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String existingPassword = "100"; // Password entered by user
        String dbPassword       = "$2a$10$/IfuSLaP7JRaSIoXeuqFFO8clWHYE1qXhxDHt9xUOGFifOyiYV4ne"; // Load hashed DB password

        for (int i = 0; i < 20; i++) {
            if (passwordEncoder.matches(existingPassword, passwordEncoder.encode("100"))) {
                System.out.println("good");
            } else {
                System.out.println("bad");
            }
        }

    }
}

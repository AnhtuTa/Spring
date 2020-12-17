package hello;

import java.io.IOException;

public class CommandDemo {

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("ls -la");
    }
}

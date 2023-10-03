package ex5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new ChatWindow();
        try {
            FileWriter fileWriter = new FileWriter("chat_log.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

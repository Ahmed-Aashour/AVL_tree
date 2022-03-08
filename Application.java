import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {
    public void Load()
    {
        File file = new File("dictionary.txt");
        try (Scanner myReader = new Scanner(file)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

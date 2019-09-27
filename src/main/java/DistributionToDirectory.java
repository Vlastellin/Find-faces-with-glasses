import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DistributionToDirectory {
    String filePath = "D:\\TEST\\test.txt";
    BufferedReader bf = new BufferedReader(new FileReader(filePath));

    public DistributionToDirectory() throws FileNotFoundException {
    }
}

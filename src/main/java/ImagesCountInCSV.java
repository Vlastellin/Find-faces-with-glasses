import au.com.bytecode.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ImagesCountInCSV extends ImageCountInDirectory {

    CSVReader reader = new CSVReader(new FileReader("D:\\TEST\\list_attr_celeba.csv"), ',', '"', 1);
    public List<String[]> allRows = reader.readAll();
    private String [] line = new String[0];
    private int countOfFaceWithGlassesInCSV = 0;
    private int countFaceNotWithGlassesInCSV = 0;

    public ImagesCountInCSV() throws IOException {
    }


    public void showCountOfImages(){
        countFaceNotWithGlassesInCSV = 0;
        countOfFaceWithGlassesInCSV = 0;
        for(int i=0;i<allRows.size();i++){
            line = Arrays.toString(allRows.get(i)).split(", ");
            if(Integer.parseInt(line[16])>0)
                countOfFaceWithGlassesInCSV++;
            else
                countFaceNotWithGlassesInCSV++;
        }
    }

    public int getCountOfFaceWithGlassesInCSV(){
        showCountOfImages();
        return countOfFaceWithGlassesInCSV;
    }
    public int getCountFaceNotWithGlassesInCSV(){
        showCountOfImages();
        return countFaceNotWithGlassesInCSV;
    }
}

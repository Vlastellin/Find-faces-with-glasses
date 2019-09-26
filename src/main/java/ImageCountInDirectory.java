import java.io.File;

public class ImageCountInDirectory {

    File listFile = new File("D:\\TEST\\img_align_celeba");
    File exportFiles[] = listFile.listFiles();
    String[] names = new String[exportFiles.length];
    public int countOfImagesInDirectory = 0;

    public int showCountImagesInDirectory(){
        countOfImagesInDirectory = 0;
        for(int i = 0; i < names.length; i++){
            names[i] = exportFiles[i].getName();
            countOfImagesInDirectory++;
        }
        return countOfImagesInDirectory;
    }
}

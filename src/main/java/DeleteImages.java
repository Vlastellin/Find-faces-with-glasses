import au.com.bytecode.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeleteImages extends ImageCountInDirectory{

    ImagesCountInCSV imagesCountInCSV = new ImagesCountInCSV();
    ImageCountInDirectory imageCountInDirectory = new ImageCountInDirectory();
    CSVReader reader = new CSVReader(new FileReader("D:\\TEST\\list_attr_celeba.csv"), ',', '"', 1);
    private String pathOfDirectoryImage = "D:\\TEST\\img_align_celeba";
    public List<String[]> allRows = reader.readAll();
    private String [] line = new String[0];
    private int countFaceNotWithGlassesInCSV = imagesCountInCSV.getCountFaceNotWithGlassesInCSV();
    private int countOfFaceWithGlassesInCSV = imagesCountInCSV.getCountOfFaceWithGlassesInCSV();
    private List<Faces> listAllFace = new ArrayList<Faces>();
    private File fileOfFaceWithNotGlasses;


    public DeleteImages() throws IOException {
    }

    public int findEqualsName(String name){
        for(Faces faces: listAllFace){
            if(faces.name.substring(1).equals(name)){
                return 1;
            }
            else{
                return 0;
            }
        }
        return 0;
    }


    public void greatBalanse() {

        int count=0;
        for(int i = 0; i<allRows.size();i++){
            line = Arrays.toString(allRows.get(i)).split(", ");
            if(Integer.parseInt(line[16])<0 && count<countOfFaceWithGlassesInCSV){
                listAllFace.add(new Faces(line[0], line[16]));
                count++;
            }
            if(Integer.parseInt(line[16])>0){
                listAllFace.add(new Faces(line[0],line[16]));
            }
        }
        for(int i=0; i<allRows.size();i++){
            line = Arrays.toString(allRows.get(i)).split(", ");
            if(countFaceNotWithGlassesInCSV >= countOfFaceWithGlassesInCSV && findEqualsName(line[0].substring(1))==0  && Integer.parseInt(line[16])<0){
                String deleteFile = pathOfDirectoryImage+"\\"+line[0].substring(1);
                fileOfFaceWithNotGlasses = new File(deleteFile);
                fileOfFaceWithNotGlasses.delete();
                countOfImagesInDirectory--;
                countFaceNotWithGlassesInCSV--;
            }
        }

        for(Faces face : listAllFace){
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("text.txt",true));
                bw.write(face.name.substring(1) + ", " + face.flag + "\n");
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        System.out.println("GREATBALANSE DONE");
        /*System.out.println(countOfImagesInDirectory);
        System.out.println(countOfFaceWithGlassesInCSV);
        System.out.println(listAllFace.size());*/
    }
}

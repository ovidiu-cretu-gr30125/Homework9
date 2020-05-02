package Exercise3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class AccessLogDisplay {
    List<AccessLog> accessLogList = new ArrayList<AccessLog>();

    List<String> textFiles = new ArrayList<>();

    public int i=0;

    /**
     * put all the files in the array that have the .dat extension
     * @param directory the path to the files
     * @return the list of the files
     */
    public List<String> textFiles(String directory) {
        File dir = new File(directory);
        for (File file : dir.listFiles()) {
            if (file.getName().endsWith((".dat"))) {
                textFiles.add(file.getName());
            }
        }
        return textFiles;
    }

    /**
     * deserializable the files
     */
    public void readFile() {
        textFiles("C:\\Users\\cretu\\iCloudDrive\\Desktop\\ISP\\lab-9-isp-ovidiu-cretu-gr30125");
        int n = textFiles.size();
        while(i<n) {
            try (
                    final FileInputStream fileInputStream = new FileInputStream(textFiles.get(i));
                    final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ) {
                final AccessLog accessLog = (AccessLog) objectInputStream.readObject();
                accessLogList.add(accessLog);
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * display the logs from the accessLog list
     */
    public void displayAccessLogList(){
        System.out.println(accessLogList.toString());
    }
}

package assessment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileIO {

    public Set<String> listFilesinDirectory (String directoryName){
        return Stream.of(new File(directoryName).listFiles())
                    .filter(file -> !file.isDirectory())
                    .map(File::getName)
                    .collect(Collectors.toSet());
    }

    public List<String> readFile(String fileName) throws IOException{
        List<String> wordList = new LinkedList<>();

        Path p = Paths.get(fileName);
        File file = p.toFile();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        wordList = br.lines()
                    .filter(l -> !l.isEmpty())
                    .map(l -> l.replaceAll("[^a-zA-Z0-9\\s]", ""))
                    .flatMap(l -> Stream.of(l.split("\\s+")))
                    .map(String :: trim)
                    .map(String :: toLowerCase)
                    .collect(Collectors.toList());
        
        br.close();

        return wordList;
    }

    public void writeToFile(List<List<String>> content, String fileName) throws IOException{
        String fullFilePath = System.getProperty("user.dir") + File.separator + fileName;
        FileWriter fw = new FileWriter(fullFilePath);

        for(List<String> list : content){
            for(String s : list){
                fw.write(s);
            }
        }
        
        fw.flush();
        fw.close();
    }
    
}

package assessment;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        
        // enter seuss as directory
        String directoryName = args[0];

        FileIO fileIO = new FileIO();
        Set<String> filesInDir = fileIO.listFilesinDirectory(directoryName);

        List<List<String>> wordsListInAllFiles = new LinkedList<>();

        for(String fileName : filesInDir){
            List<String> wordList = fileIO.readFile(directoryName + File.separator + fileName);
            wordsListInAllFiles.add(wordList);
        }

        TextProcessor tp = new TextProcessor();
        for (List<String> list : wordsListInAllFiles){
            // use textprocessor
            Map<String, Map<String,Integer>> wordMap = tp.analyse(list);
            System.out.println(wordMap);
            tp.printProbability(wordMap);
        }

    }
    
}

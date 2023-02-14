package assessment;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TemporaryMain {

    public static void main(String[] args) throws IOException {
        
        String directoryName = "seuss";

        FileIO fileIO = new FileIO();
        // Set<String> filesInDir = fileIO.listFilesinDirectory(directoryName);
        // List<List<String>> wordsListInAllFiles = new LinkedList<>();

        // for(String fileName : filesInDir){
        //     List<String> wordList = fileIO.readFile(directoryName + File.separator + fileName);
        //     wordsListInAllFiles.add(wordList);
            
        // }

        // System.out.println(wordsListInAllFiles.get(1));

        // for(List<String> list : wordsListInAllFiles){}
        
        

        List<String> wordList = new LinkedList<>();
        wordList.add("fox");
        wordList.add("socks");
        wordList.add("fox");
        wordList.add("in");
        wordList.add("fox");
        wordList.add("socks");

        System.out.println(wordList);

        TextProcessor tp = new TextProcessor();

        Map<String, Map<String,Integer>> wordMap = tp.analyse(wordList);
        System.out.println(wordMap);

        tp.printProbability(wordMap);
    }
    
}

package assessment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextProcessor {

    public Map<String, Map<String,Integer>> analyse(List<String> list){

        Map<String, Map<String,Integer>> wordAndNextWordsCount = new HashMap<>();
        
        for(int i = 0; i < list.size()-1; i++){
            Map<String, Integer> nextWordProbability = new HashMap<>();
            String currentWord = list.get(i);
            String nextWord = list.get(i+1);

            if(!wordAndNextWordsCount.containsKey(currentWord)){
                nextWordProbability.put(nextWord, 1);
                wordAndNextWordsCount.put(currentWord, nextWordProbability);
            } else {
                nextWordProbability = wordAndNextWordsCount.get(currentWord);
                if(!nextWordProbability.containsKey(nextWord)){
                    nextWordProbability.put(nextWord,1);
                    wordAndNextWordsCount.put(currentWord, nextWordProbability);
                } else {
                    nextWordProbability.put(nextWord, nextWordProbability.get(nextWord)+1);
                    wordAndNextWordsCount.put(currentWord, nextWordProbability);
                }
            }
        }
    
        return wordAndNextWordsCount;
    }
    
    public void printProbability (Map<String, Map<String,Integer>> wordAndNextWordsCount){
        for(Map.Entry<String, Map <String, Integer>> entry : wordAndNextWordsCount.entrySet()){
            System.out.println(entry.getKey());
            
            int totalCount = 0;
            for (Map.Entry<String,Integer> nextWordEntry : entry.getValue().entrySet()){
                
                totalCount+=nextWordEntry.getValue();
            }
            for (Map.Entry<String,Integer> nextWordEntry : entry.getValue().entrySet()){
                System.out.printf("\t%s %.2f\n", nextWordEntry.getKey(), (float)nextWordEntry.getValue()/totalCount);
            }

        }
    }
}

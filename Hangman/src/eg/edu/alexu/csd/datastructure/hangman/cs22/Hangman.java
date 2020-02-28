package eg.edu.alexu.csd.datastructure.hangman.cs22;
import java.io.*;
import java.util.Random;

public class Hangman implements IHangman{
    public String [] dictionary;
    public String chosenWord;
    private String secretWord;
    public String updateWord;
    private int maxNum;
    private Exception Exception;

    public static String[] readFile(String dicName, int dicLn) throws IOException {
        FileReader fileReader = new FileReader(dicName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String dic [] = new String[dicLn];
        int i = 0;
        while((dic[i] = bufferedReader.readLine()) != null) {
            i++;
        }
        fileReader.close();
        return dic;
    }
    public void setDictionary(String[] words){
        dictionary = words;
    }

    public String selectRandomSecretWord() {
        Random r = new Random();
        int low = 0;
        int high = 197;
        int result = r.nextInt(high-low) + low;
        chosenWord =  dictionary[result];
        String secretWord = "";
        for(int i = 0; i < chosenWord.length(); i++){
            if(chosenWord.charAt(i) != ' '){
                secretWord = secretWord + "_ ";
            }
            else{
                secretWord = secretWord + "  ";
            }

        }
        updateWord = secretWord;
        return secretWord;
    }


    public String guess(Character c) throws Exception {
        boolean flag = true;
        int counter = 0;
        for (int i = 0; i < chosenWord.length(); i++){
            char temp = chosenWord.charAt(i);
            if(!(Character.isLetter(temp) || Character.isWhitespace(temp))) {
                throw Exception;
            }
        }
        for (int i = 0; i < chosenWord.length(); i++){
            char temp = chosenWord.charAt(i);
            if(Character.toLowerCase(temp) == Character.toLowerCase(c)) {
                int index = i*2;
                updateWord = updateWord.substring(0,index) + c + updateWord.substring(index+1);
                flag = false;
            }
        }
        if (flag){
            maxNum--;
        }
        if(maxNum > 0 ){
            return updateWord;
        }
        else{
            return null;
        }
    }

    public void setMaxWrongGuesses(Integer max) {
        if(max >= 1){
            maxNum = max;
        }
        else{
            maxNum = 1;
        }
    }
    public int win(String word){ //to check for empty spaces
        int counter = 0;
        for (int i = 0; i < word.length(); i++){
            if(word.charAt(i) != '_'){
                counter++;
            }
        }
        if(counter == word.length()){
            return  1;
        }
        else {
            return 0;
        }
    }
}

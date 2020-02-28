package eg.edu.alexu.csd.datastructure.hangman.cs22;

import jdk.swing.interop.SwingInterOpUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import static eg.edu.alexu.csd.datastructure.hangman.cs22.Hangman.readFile;

public class main {
    public static void main(String[] args) throws Exception {
        Hangman game = new Hangman();
        Scanner move = new Scanner(System.in);
        char c ;
        String flag = "";
        game.setMaxWrongGuesses(10);
        game.setDictionary(readFile("dictionary.txt",198));
        System.out.println(game.selectRandomSecretWord());
        //System.out.println(game.chosenWord);//to see the selected word remove the comment sign
        while(flag != null ) {
            System.out.print("enter letter:");
            c = move.next().charAt(0);
            flag = game.guess(c);
            if(flag == null){
                break;
            }
            System.out.println(flag);
            if(game.win(flag) == 1){
                break;
            }
        }
    }
}

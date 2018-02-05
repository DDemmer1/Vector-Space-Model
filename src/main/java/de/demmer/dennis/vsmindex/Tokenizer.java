package de.demmer.dennis.vsmindex;

import java.io.*;
import java.text.BreakIterator;
import java.util.*;

public class Tokenizer {

    /**
     * Speichert den Inhalt eines Files in einen String
     *
     * @param url     Die url des in den String zu schreibenden Files.
     * @param encoding Das Encoding für den Text. Wird vom InputStreamReader benötigt.
     * @return Den Inhalt des Textes als String.
     * @throws UnsupportedEncodingException Wenn das Encoding nicht unterstützt wird
     * @throws FileNotFoundException        Wenn das File nicht gefunden wird
     * @throws IOException                  Bei jeglicher Form von Fehlern beim schreiben
     */

    public static String getText(String url, String encoding)
            throws UnsupportedEncodingException, FileNotFoundException,
            IOException {
        File file = new File(url);

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
        StringBuffer stringBuffer = new StringBuffer();
        String line = "";

        //Zeile für Zeile den Text in den StringBuffer schreiben
        while ((line = reader.readLine()) != null) {
            //Hinzufügen des System spezifischen Zeilenumbruch befehls
            stringBuffer.append(line).append(System.lineSeparator());
        }

        reader.close();
        return stringBuffer.toString();
    }


    /**
     * Ein Tokenisierer der ein sortiertes Set von Strings zurückgibt. Alle Wörter im Set sind lower case.
     *
     * @param text Zu Tokenisierender Text.
     * @param wordSet Zu befüllendes Set.
     * @return Set mit Strings, die die Wörter des Textes beinhaltet.
     */

    public Set<String> getWordSet(String text, SortedSet<String> wordSet){

        text = text.toLowerCase(Locale.ENGLISH);

        BreakIterator iterator = BreakIterator.getWordInstance(Locale.ENGLISH);
        iterator.setText(text);
        int start = iterator.first();
        int end = iterator.next();

        while (end != BreakIterator.DONE) {
            String currentWord = text.substring(start, end);

            if (Character.isLetter(currentWord.charAt(0)) && currentWord.length() >= 1) {

                wordSet.add(currentWord);
            }

            start = end;
            end = iterator.next();

        }

        return wordSet;

    }


    public List<String> getWordList(String text) {

        List<String> wordList = new ArrayList<>();
        text = text.toLowerCase(Locale.ENGLISH);

        BreakIterator iterator = BreakIterator.getWordInstance(Locale.ENGLISH);
        iterator.setText(text);
        int start = iterator.first();
        int end = iterator.next();

        while (end != BreakIterator.DONE) {
            String currentWord = text.substring(start, end);

            if (Character.isLetter(currentWord.charAt(0)) && currentWord.length() >= 1) {
                wordList.add(currentWord);
            }
            start = end;
            end = iterator.next();
        }


            return wordList;
    }




    }

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Translator {

    private static Formatter formatter;

    static {
        try {
            formatter = new Formatter("output.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)  {
        try {
            Scanner in = new Scanner(new File(args[0]));
            // :NOTE: # должно быть два файла со словарем и с текстом, который требуется перевести
            // :NOTE: * не игнорируется регистр
            // :NOTE: # Максиммально длины левой части, а не правой
            // :NOTE: * обработка ошибок при разборе словаря

            while (in.hasNextLine()) {
                String line = in.nextLine();


                findTranslation(line);
            }
            formatter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found, specify the source files");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You did not enter a file name, please enter it");
        }
    }

    private static void findTranslation(String line) {
        if (line.length() == 0) {
            formatter.format("\n");
            return;
        }

        if (severalTranslationOptions(line)) {
            int max = 0;
            int indexOfMax = 0;
            ArrayList<String> variants = translations(line);

            for (int i = 0; i < variants.size(); i++) {
                if (variants.get(i).length() > max) {
                    max = variants.get(i).length();
                    indexOfMax = i;
                }
            }

            formatter.format(variants.get(indexOfMax) + '\n');
            return;
        }
        StringBuilder translationWord = new StringBuilder();
        boolean separatingMark = false;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '|') {
                i += 3;
                while (line.charAt(i) != '>') {
                    translationWord.append(line.charAt(i));
                    i++;
                }
                if (thereIsNotOnePieceOfTextInTheLine(line)) {
                    formatter.format(translationWord + " ");
                } else {
                    formatter.format(translationWord + "\n");
                }
                translationWord.setLength(0);
                separatingMark = true;
            }
        }
        formatter.format("\n");

        if (!separatingMark) {
            formatter.format(line.substring(1, line.length() - 1) + '\n');
        }
    }

    private static boolean thereIsNotOnePieceOfTextInTheLine(String line) {
        int numberSharedBySign = 0;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '<') {
                numberSharedBySign++;
            }
        }

        return numberSharedBySign > 1;
    }

    private static boolean severalTranslationOptions(String line) {
        int balanceOpen = 0;
        int balanceClose = 0;
        // :NOTE: * добавление '<' и '>' нарушает формат словаря
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '|') {
                for (int j = ++i; j < line.length(); j++) {
                    if (line.charAt(j) == '|') {
                        return false;
                    } else if (line.charAt(j) == '<') {
                        balanceOpen++;
                    } else if (line.charAt(j) == '>') {
                        balanceClose++;
                    }
                }
                return balanceOpen == balanceClose  && balanceClose > 1;
            }
        }

        return false;
    }

    private static ArrayList<String> translations(String line) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '|') {
                for (int j = ++i; j < line.length(); j++) {
                    if (line.charAt(j) == '<') {
                        j++;
                        while (line.charAt(j) != '>') {
                            sb.append(line.charAt(j));
                            j++;
                        }
                        result.add(sb.toString());
                        sb.setLength(0);
                    }
                }
            }
        }

        return result;
    }
}

package md2html;

import md2html.markup.Editable;
import md2html.markup.Paragraph;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Md2Html {
    public static void main(String[] args) {
        final String inputFile = args[0];
        final String outputFile = args[1];

        try {
            MarkdownScanner in = new MarkdownScanner(inputFile);
            try {
                ArrayList<Editable> book = new ArrayList<>();

                while (in.hasNextParagraph()) {
                    book.add(in.nextParagraph());
                }
                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(outputFile),
                                StandardCharsets.UTF_8
                        )
                );
                try {
                    for (Editable a : book) {
                        StringBuilder ans = new StringBuilder();
                        a.toHtml(ans);
                        out.write(ans.toString());
                        out.newLine();
                    }
                } finally {
                    out.close();
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
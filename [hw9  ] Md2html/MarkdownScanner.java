package md2html;

import md2html.markup.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.util.Map.entry;

public class MarkdownScanner {

    Scanner in;
    int cnt = 0;

    final String strong1Board = "__";
    final String strong2Board = "**";
    final String emphasis1Board = "_";
    final String emphasis2Board = "*";
    final String strikeoutBoard = "--";
    final String codeBoard = "`";
    final String textBoard = "";
    final String linkOpenBoard = "[";
    final String linkCloseBoard = "]";

    HashMap<String, Integer> boardsTypes = new HashMap<>(Map.ofEntries(
            entry(strong1Board, 1),
            entry(strong2Board, 2),
            entry(emphasis1Board, 3),
            entry(emphasis2Board, 4),
            entry(strikeoutBoard, 5),
            entry(codeBoard, 6),
            entry(textBoard, 7),
            entry(linkOpenBoard, 8),
            entry(linkCloseBoard, 8)
    ));

    ArrayList<String> boards = new ArrayList<>(List.of(
            strong1Board, strong2Board, emphasis1Board, emphasis2Board, strikeoutBoard, codeBoard
    ));

    HashMap<String, String> specSymbolsTypes = new HashMap<>(Map.ofEntries(
            entry("&", "&amp;"),
            entry("<", "&lt;"),
            entry(">", "&gt;"),
            entry("\\", "")
    ));

    ArrayList<String> specSymbols = new ArrayList<>(List.of(
            "&", "<", ">", "\\"
    ));

    MarkdownScanner(String inputFile) throws IOException {
        in = new Scanner(new File(inputFile), StandardCharsets.UTF_8);
    }

    private boolean isEmpty(String a) {
        boolean result = true;
        for (char x : a.toCharArray()) {
            if (!Character.isWhitespace(x)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private int isHeadline(StringBuilder a) {
        Scanner localScan = new Scanner(a.toString());
        String firstWord = localScan.next();
        if (firstWord.length() <= 6 && firstWord.chars().distinct().count() == 1
                && firstWord.charAt(0) == '#' && !Character.isWhitespace(a.charAt(0))) {
            return firstWord.length();
        }
        return 0;
    }

    private StringBuilder getParText() throws IOException {
        StringBuilder parText = new StringBuilder();

        boolean isParStarted = false;
        boolean isParFound = false;

        while (in.hasNextLine() && !isParFound) {
            String line = in.nextLine();
            if (!isEmpty(line)) {
                isParStarted = true;
                parText.append(line).append(System.lineSeparator());
            } else {
                isParFound = isParStarted;
            }
        }

        int sepSize = System.lineSeparator().length();
        if (parText.substring(parText.length() - sepSize).equals(System.lineSeparator())) {
            parText = new StringBuilder(parText.substring(0, parText.length() - sepSize));
        }

        return parText;
    }

    private void updatePositions(List<MyCont> p, StringBuilder x) {
        if (p.size() == 0) {
            p.add(new MyCont(0, textBoard));
            p.add(new MyCont(x.length(), textBoard));
        } else {
            int firstPos = p.get(0).n;

            p.add(0, new MyCont(firstPos, textBoard));
            p.add(0, new MyCont(0, textBoard));

            p.add(new MyCont(p.get(p.size() - 1).n + p.get(p.size() - 1).type.length(), textBoard));
            p.add(new MyCont(x.length(), textBoard));
        }
    }

    private ArrayList<MyCont> merge(ArrayList<MyCont> a, ArrayList<MyCont> b) {
        ArrayList<MyCont> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < a.size() || j < b.size()) {
            if (j == b.size() || i < a.size() && a.get(i).n < b.get(j).n) {
                result.add(a.get(i++));
            } else {
                result.add(b.get(j++));
            }
        }
        return result;
    }

    private ArrayList<MyCont> getPositions(StringBuilder x) {
        ArrayList<Integer> sq = new ArrayList<>();
        ArrayList<MyCont> result = new ArrayList<>();

        for (int i = 0; i < x.length(); i++) {
            if (x.charAt(i) == ')') {
                sq.add(i);
            }
        }

        int lastIndex = 0;
        int deletedSize = 0;
        Stack<Integer> lastRect = new Stack<>();

        for (int i = 0; i < x.length(); i++) {
            if (lastIndex < sq.size() && sq.get(lastIndex) - deletedSize < i) {
                lastIndex++;
            }

            if (x.charAt(i) == '[') {
                lastRect.push(i);
            } else if (x.charAt(i) == '(') {
                if (i > 0 && x.charAt(i - 1) == ']' &&
                        !lastRect.isEmpty() && lastIndex < sq.size()) {
                    result.add(new MyCont(lastRect.peek(), "[", x.substring(i + 1, sq.get(lastIndex) - deletedSize)));
                    result.add(new MyCont(i - 1, "]"));

                    x = x.delete(i, sq.get(lastIndex) - deletedSize + 1);
                    deletedSize = sq.get(lastIndex) - i + 1;
                    lastRect.pop();
                }
            }
        }

        result = merge(result, parsePositions(x));
        updatePositions(result, x);
        return result;
    }

    boolean isValidCharacter(StringBuilder x, String s, int c) {
        return ((c - 1) >= 0 && !Character.isWhitespace(x.charAt(c - 1)) || c + s.length() < x.length() && !Character.isWhitespace(x.charAt(c + s.length())))
                && ((c - 1 < 0 || x.charAt(c - 1) != s.charAt(0) && x.charAt(c - 1) != '\\')
                && (c + s.length() >= x.length() || x.charAt(c + s.length()) != s.charAt(0)));
    }

    private ArrayList<MyCont> parsePositions(StringBuilder x) {
        ArrayList<MyCont> result = new ArrayList<>();
        for (String s : boards) {
            int c = x.indexOf(s, 0);
            while (c != -1) {
                if (isValidCharacter(x, s, c)) {
                    result.add(new MyCont(c, s));
                }
                c = x.indexOf(s, c + s.length());
            }
        }
        Collections.sort(result);
        return result;
    }

    private String editStr(String text) {
        StringBuilder result = new StringBuilder(text);
        for (String s : specSymbols) {
            int start = 0;
            int c = result.indexOf(s, start);
            while (c != -1) {
                result.replace(c, c + s.length(), specSymbolsTypes.get(s));
                start = c + specSymbolsTypes.get(s).length();
                c = result.indexOf(s, start);
            }
        }
        return result.toString();
    }

    private Editable parsePar(StringBuilder parText, ArrayList<MyCont> positions, int c) {
        Stack<Editable> stack = new Stack<>();
        Stack<Integer> types = new Stack<>();
        Editable result;

        int lastAddedPos = 0;

        if (c != 0) {
            result = new Headline(c);
        } else {
            result = new Paragraph();
        }

        for (int i = 0; i < positions.size(); i++) {
            int curType = boardsTypes.get(positions.get(i).type);
            int curPos = positions.get(i).n;

            if (stack.isEmpty()) {
                result.add(new Text(editStr(parText.substring(lastAddedPos, curPos))));
                lastAddedPos = curPos + positions.get(i).type.length();

                stack.push(Factory.createWord(curType, positions.get(i).data));
                types.push(curType);
            } else if (curType == types.peek()) {
                MyCont p1 = positions.get(i - 1);
                MyCont p2 = positions.get(i);

                stack.peek().add(new Text(editStr(parText.substring(p1.n + p1.type.length(), p2.n))));
                lastAddedPos = p2.n + p2.type.length();

                Editable top = stack.peek();
                stack.pop();
                types.pop();

                if (stack.isEmpty()) {
                    result.add(top);
                } else {
                    stack.peek().add(top);
                }
            } else if (curType != types.peek()) {
                MyCont p1 = positions.get(i - 1);
                MyCont p2 = positions.get(i);

                stack.peek().add(new Text(editStr(parText.substring(p1.n + p1.type.length(), p2.n))));
                lastAddedPos = p2.n + p2.type.length();

                stack.push(Factory.createWord(curType, positions.get(i).data));
                types.push(curType);
            }
        }

        while (!stack.isEmpty()) {
            Editable top = stack.peek();
            stack.pop();

            if (stack.isEmpty()) {
                result.add(top);
            } else {
                stack.peek().add(top);
            }
        }

        return result;
    }

    public Editable nextParagraph() throws IOException {
        cnt++;
        StringBuilder parText = getParText();

        int c = isHeadline(parText);
        if (c != 0) {
            parText = new StringBuilder(parText.substring(c + 1));
        }

        ArrayList<MyCont> positions = getPositions(parText);
        return parsePar(parText, positions, c);
    }

    public boolean hasNextParagraph() {
        return in.hasNext();
    }

    public void close() {
        in.close();
    }

}

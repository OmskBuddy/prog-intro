package md2html.markup;

public class Factory {

    public static Editable createWord(int number, String data) {
        Editable result = null;

        switch (number) {
            case 1, 2 :
                result = new Strong();
                break;
            case 3, 4 :
                result = new Emphasis();
                break;
            case 5 :
                result = new Strikeout();
                break;
            case 6 :
                result = new Code();
                break;
            case 7 :
                result = new Text();
                break;
            case 8 :
                result = new Link(data);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + number);
        }

        return result;
    }

}

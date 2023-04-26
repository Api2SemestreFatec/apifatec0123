import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class LimitarDocument extends PlainDocument {
    private int limiteCaracteres;

    public LimitarDocument(int limiteCaracteres) {
        this.limiteCaracteres = limiteCaracteres;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        if ((getLength() + str.length()) <= limiteCaracteres) {
            super.insertString(offset, str, attr);
        }
    }
}


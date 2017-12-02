package lexico;

public class Token {
    public final int tag;
    public String lexema;

    public Token (int tag){
        this.tag = tag;
    }

    public Token (int tag, String lexema){
        this.tag = tag;
        this.lexema = lexema;
    }

    public String getLexema() {
        return lexema;
    }

    public int getTag() {
        return tag;
    }

    @Override
    public String toString(){
        return "<" + Tag.tag2String(tag) + ", " + lexema + ">";
    }
}

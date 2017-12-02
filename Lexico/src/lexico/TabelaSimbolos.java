package lexico;

import java.util.Collection;
import java.util.HashMap;

public class TabelaSimbolos {

    private static TabelaSimbolos INSTANCE;
    private HashMap<String, Token> hashMap;

    private TabelaSimbolos() {
        hashMap = new HashMap<String, Token>();
    }

    public static TabelaSimbolos getTabelaSimbolo() {

        if (INSTANCE == null) {

            INSTANCE = new TabelaSimbolos();
            return INSTANCE;

        }

        return INSTANCE;
    }

    public void put(Token token) {
        hashMap.put(token.getLexema(), token);
    }

    public Token get(String lexema) {

        Token token = hashMap.get(lexema);
        return token;

    }

    @Override
    public String toString() {

        String str = "******** TABELA DE SIMBOLOS *********\n\n";
        Collection<Token> tokens = hashMap.values();

        for (Token token : tokens) {
            str = str + token + "\n";
        }

        return str;

    }
}

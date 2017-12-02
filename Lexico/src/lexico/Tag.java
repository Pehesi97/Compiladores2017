package lexico;

import java.util.HashMap;
import java.util.Map;

public class Tag {
    private static Map<Integer,String> nomes = new HashMap<>();
    public final static int
            PROGRAM=256,
            END=257,
            INT=258,
            STRING=259,
            IF=260,
            THEN=261,
            ELSE=262,
            DO=263,
            WHILE=264,
            SCAN=265,
            PRINT=266,

            EQ=267,
            GE=268,
            LE=269,
            NE=270,
            OR=271,
            AND=272,

            INT_CONST=273,
            IDENTIFIER=274,
            STR_LITERAL=275,
            EoF=276;
    static {
        nomes.put(PROGRAM, "PROGRAM");
        nomes.put(END, "END");
        nomes.put(INT, "INT");
        nomes.put(STRING, "STRING");
        nomes.put(IF, "IF");
        nomes.put(THEN, "THEN");
        nomes.put(ELSE, "ELSE");
        nomes.put(DO, "DO");
        nomes.put(WHILE, "WHILE");
        nomes.put(SCAN, "SCAN");
        nomes.put(PRINT, "PRINT");

        nomes.put(EQ, "EQ");
        nomes.put(GE, "GE");
        nomes.put(LE, "LE");
        nomes.put(NE, "NE");
        nomes.put(OR, "OR");
        nomes.put(AND, "AND");

        nomes.put(INT_CONST, "INT_CONST");
        nomes.put(IDENTIFIER, "IDENTIFIER");
        nomes.put(STR_LITERAL, "STR_LITERAL");
        nomes.put(EoF, "EoF");
    }

    public static String tag2String(int i){
        if(i < 256){
            return "" + (char)i;
        } else {
            return nomes.get(i);
        }
    }
}

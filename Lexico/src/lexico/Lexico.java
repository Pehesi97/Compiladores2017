package lexico;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Lexico {

    public static int numLinha = 1;
    private TabelaSimbolos TABELA_DE_SIMBOLOS = TabelaSimbolos.getTabelaSimbolo();
    private FileReader fileReader;
    private char CH_CORRENTE = ' ';
    private boolean FIM_DE_ARQUIVO = false;

    public Lexico(String nomeArquivo) {

        try {
            fileReader = new FileReader(nomeArquivo);
            System.out.println(fileReader);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo nao encontrado");
            System.exit(1);
        }

        insereTokensReservadas();
    }
    void erro (String e){
        System.out.println(e);
        System.exit(1);
    }
    private void insereTokensReservadas() {
        insereToken(new Token(Tag.PROGRAM, "program"));
        insereToken(new Token(Tag.END, "end"));
        insereToken(new Token(Tag.INT, "int"));
        insereToken(new Token(Tag.STRING, "string"));
        insereToken(new Token(Tag.IF, "if"));
        insereToken(new Token(Tag.THEN, "then"));
        insereToken(new Token(Tag.ELSE, "else"));
        insereToken(new Token(Tag.DO, "do"));
        insereToken(new Token(Tag.WHILE, "while"));
        insereToken(new Token(Tag.SCAN, "scan"));
        insereToken(new Token(Tag.PRINT, "print"));

        insereToken(new Token(Tag.EQ, "=="));
        insereToken(new Token(Tag.GE, ">="));
        insereToken(new Token(Tag.LE, "<="));
        insereToken(new Token(Tag.NE, "!="));
        insereToken(new Token(Tag.OR, "||"));
        insereToken(new Token(Tag.AND, "&&"));
    }

    private void insereToken(Token token) {
        TABELA_DE_SIMBOLOS.put(token);
    }

    private int readch() throws IOException {

        int intCh = fileReader.read();
        CH_CORRENTE = (char) intCh;

        if (CH_CORRENTE == '\n') {
            numLinha++;
        }

        if (intCh == -1) {
            FIM_DE_ARQUIVO = true;
        }

        return intCh;

    }

    private int readch(char c) throws IOException {

        int intCh = readch();

        if (c == intCh) {
            CH_CORRENTE = ' ';
        }

        return intCh;

    }

    private void getCaracterIgnorado() throws IOException {
        while (CH_CORRENTE == ' ' || CH_CORRENTE == '\t' || CH_CORRENTE == '\r' || CH_CORRENTE == '\b' || CH_CORRENTE == '\n') {
            readch();
        }
    }

    private Token getOperador() throws IOException {
        switch (CH_CORRENTE) {
            case '=': {
                if (readch('=') == '=') {
                    return new Token(Tag.EQ, "==");
                } else {
                    return new Token('=', "=");
                }
            }
            case '>': {
                if (readch('=') == '=') {
                    return new Token(Tag.GE, ">=");
                } else {
                    return new Token('>', ">");
                }
            }
            case '<': {
                if (readch('=') == '=') {
                    return new Token(Tag.LE, "<=");
                } else {
                    return new Token('<', "<");
                }
            }
            case '&': {
                if (readch('&') == '&') {
                    return new Token(Tag.AND, "&&");
                } else {
                    erro("Erro caracter inesperado na linha "+numLinha);
                }
            }
            case '|': {
                if (readch('|') == '|') {
                    return new Token(Tag.OR, "||");
                } else {
                    erro("Erro caracter inesperado na linha "+numLinha);
                }
            }
            case '!': {
                if (readch('=') == '=') {
                    return new Token(Tag.NE, "!=");
                } else {
                    return new Token('!', "!");
                }
            }
            case '+': {
                CH_CORRENTE = ' ';
                return new Token('+', "+");
            }
            case '-': {
                CH_CORRENTE = ' ';
                return new Token('-', "-");
            }
            case '*': {
                CH_CORRENTE = ' ';
                return new Token('*', "*");
            }

            default:
                return null;
        }

    }

    private Token getComentario() throws IOException {
        if (CH_CORRENTE == '/') {
            readch();
            if (CH_CORRENTE == '/') {
                while (readch('\n') != '\n') {
                    if (FIM_DE_ARQUIVO) {
                        break;
                    }
                }
                return nextToken();

            } else if(CH_CORRENTE == '*'){
                while (true) {
                    readch();
                    if (CH_CORRENTE == -1) {
                        erro("Comentario de mais de uma linha nao fechado");
                    }
                    if (CH_CORRENTE == '*') {
                        if (readch('/') == '/') {
                            break;
                        }
                    }
                }
                return nextToken();

            } else {
                return new Token('/', "/");
            }

        }

        return null;
    }

    private Token getSimbolo() throws IOException {

        switch (CH_CORRENTE) {
            case '(':
                CH_CORRENTE = ' ';
                return new Token('(', "(");
            case ')':
                CH_CORRENTE = ' ';
                return new Token(')', ")");
            case ';':
                CH_CORRENTE = ' ';
                return new Token(';', ";");
            case ',':
                CH_CORRENTE = ' ';
                return new Token(',', ",");
        }
        return null;

    }

    private Token getLiteral() throws IOException {
        if (CH_CORRENTE == '"') {
            String literal = "";
            readch();

            while (CH_CORRENTE != '\n' && CH_CORRENTE != '"' && CH_CORRENTE != -1) {
                literal += CH_CORRENTE;
                readch();
            }

            if (CH_CORRENTE == '"') {
                readch();
                return new Token(Tag.STR_LITERAL, literal);
            } else {
                System.out.println("Literal invalido na linha " + numLinha);
                System.exit(0);
            }

        }

        return null;

    }

    private Token getNumero() throws IOException {

        String numero = "";

        if (Character.isDigit(CH_CORRENTE)) {
            while (Character.isDigit(CH_CORRENTE)){
                numero += CH_CORRENTE;
                readch();
            }
            return new Token(Tag.INT_CONST, numero);
        }
        else{
            return null;
        }
    }

    private Token getIdentificador() throws IOException {

        StringBuilder b = new StringBuilder();

        if (Character.isLetter(CH_CORRENTE)) {

            while ( Character.isLetterOrDigit(CH_CORRENTE)) {
                b.append(CH_CORRENTE);
                readch();
            }
            String id = b.toString();
            Token token = TABELA_DE_SIMBOLOS.get(id);

            if (token != null) {
                return token;
            }

            token = new Token(Tag.IDENTIFIER, id);
            TABELA_DE_SIMBOLOS.put(token);

            return token;

        } else if (CH_CORRENTE == '_') {

            b.append(CH_CORRENTE);
            readch();

            if (Character.isLetterOrDigit(CH_CORRENTE)) {

                while ( Character.isLetterOrDigit(CH_CORRENTE)) {
                    b.append(CH_CORRENTE);
                    readch();
                }

                String id = b.toString();
                Token token = TABELA_DE_SIMBOLOS.get(id);

                if (token != null) {
                    return token;
                }

                token = new Token(Tag.IDENTIFIER, id);
                TABELA_DE_SIMBOLOS.put(token);

                return token;

            }

            return null;
        }

        return null;

    }

    public Token nextToken() throws IOException {

        getCaracterIgnorado();
        Token token;
        token = getOperador();
        if (token != null) {
            return token;
        }

        token = getComentario();
        if (token != null) {
            return token;
        }

        token = getSimbolo();
        if (token != null) {
            return token;
        }

        token = getLiteral();
        if (token != null) {
            return token;
        }

        token = getNumero();
        if (token != null) {
            return token;
        }

        token = getIdentificador();
        if (token != null) {
            return token;
        }
        if (!FIM_DE_ARQUIVO) {

            System.out.println("Caractere inesperado " + (int) CH_CORRENTE + " " + CH_CORRENTE + " linha " + numLinha);
            System.exit(0);
        }else {

            return new Token(Tag.EoF,"Eof");
        }
        return null;
    }

}

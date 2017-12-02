package sintatico;

import lexico.Lexico;
import lexico.Tag;
import lexico.Token;

import java.io.IOException;

public class Sintatico {
    private Lexico lexico;
    private Token token;
    int nivel=0;

    //leitura do proximo token
    public Sintatico(Lexico lexico) throws IOException {

        this.lexico = lexico;
        token = lexico.nextToken();

    }
    public boolean oneof(Integer ...tags){
        for(Integer t:tags){
            if((token.tag)==t){
                return true;
            }
        }
        return false;
    }

    private void erro() {

        System.out.println("Erro sintático na linha " + Lexico.numLinha + " próximo ao token " + token.getLexema());
        System.exit(0);

    }

    //funcao que consome tokens
    private void eat(int tag) throws IOException {

        if (token.tag == tag) {
            imprime("eat " + token);
            token = lexico.nextToken();
        } else {
            erro();
        }

    }
    void imprime(String nome){
        for(int i=0;i<nivel;i++) {
            System.out.print('\t');
        }
        System.out.println(nome);
    }
    //program :  program [decl-list] stmt-list end
    private void program() throws IOException {
        imprime("Program");
        nivel++;
        eat(Tag.PROGRAM);
        declList();
        stmtList();
        eat(Tag.END);
        eat(Tag.EoF);
    }
    //declList :  decl {decl}
    private void declList() throws IOException {
        imprime("DeclList");
        nivel++;
        if (((token.tag) == (Tag.STRING)) || ((token.tag) == (Tag.INT))) {
            eat(token.tag);
            decl();
            eat(';');
            while(((token.tag) == (Tag.STRING)) || ((token.tag) == (Tag.INT))) {
                eat(token.tag);
                decl();
                eat(';');
            }
        } else {
            erro();
        }
        nivel--;
    }

    //decl :  type identlist     ";"
    private void decl() throws IOException {
        imprime("Decl");
        nivel++;
        switch (token.tag) {

            case Tag.IDENTIFIER:
                identList();
                break;

            default:
                erro();

        }
        nivel--;

    }

    //identList : IDENTIFIER {',' IDENTIFIER}
    private void identList() throws IOException {
        imprime("IdentList");
        nivel++;
        switch (token.tag) {

            case Tag.IDENTIFIER:
                identifier();
                while(token.tag==','){
                    eat(',');
                    identifier();
                }
                break;

            default:
                erro();

        }
        nivel--;
    }

    //identifiere : Identifier
    private void identifier() throws IOException {
        imprime("Identifier");
        nivel++;
        switch (token.tag) {

            case Tag.IDENTIFIER:
                eat(Tag.IDENTIFIER);
                break;

            default:
                erro();

        }
        nivel--;
    }

    //stmlist : stmtlist {stmtlist1}
    private void stmtList() throws IOException {
        imprime("StmList");
        nivel++;
        switch (token.tag) {

            case Tag.IDENTIFIER:
            case Tag.DO:
            case Tag.SCAN:
            case Tag.PRINT:
            case Tag.IF:
                stmt();
                eat(';');
                while(oneof(Tag.IDENTIFIER,Tag.DO,Tag.SCAN,Tag.PRINT)){
                    stmt();
                    eat(';');
                }
                break;
            default:
                erro();
        }
        nivel--;
    }

    //stmt : assign-stmt ";" | if-stmt | while-stmt | read-stmt ";" | write-stmt ";"
    private void stmt() throws IOException {
        imprime("Stmt");
        nivel++;
        switch (token.tag) {

            case Tag.IDENTIFIER:
                assignFunc();
                break;
            case Tag.IF:
                ifFunc();
                break;
            case Tag.DO:
                whileFunc();
                break;
            case Tag.SCAN:
                scanFunc();
                break;
            case Tag.PRINT:
                printFunc();
                break;
            default:
                erro();
        }
        nivel--;

    }
    //assign : identifier "=" simple_expr
    private void assignFunc() throws IOException {
        imprime("AssignFunc");
        nivel++;
        switch (token.tag) {

            case Tag.IDENTIFIER:

                identifier();
                eat('=');
                simpleExpr();
                break;

            default:
                erro();
        }
        nivel--;
    }

    //simpleExpr : term | simple-expr addop term
    private void simpleExpr() throws IOException {
        imprime("SimpleExpr");
        nivel++;
        switch (token.tag) {

            case Tag.IDENTIFIER:
            case Tag.INT_CONST:
            case Tag.STR_LITERAL:
            case '(':
            case '!':
            case '-':
                term();
                simpleExpr1();
                break;

            default:
                erro();

        }
        nivel--;

    }

    //term ->  factor-a | term1
    private void term() throws IOException {
        imprime("Term");
        nivel++;
        switch (token.tag) {

            case Tag.IDENTIFIER:
            case Tag.INT_CONST:
            case Tag.STR_LITERAL:
            case '(':
            case '!':
            case '-':
                factorA();
                term1();
                break;

            default:
                erro();

        }
        nivel--;

    }

    //factor-a ->  factor | ! factor | "-" factor
    private void factorA() throws IOException {
        imprime("FatorA");
        nivel++;
        switch (token.tag) {

            case Tag.IDENTIFIER:
            case Tag.STR_LITERAL:
            case Tag.INT_CONST:
            case '(':
                factor();
                break;

            case ('!'):
                eat('!');
                factor();
                break;

            case '-':
                eat('-');
                factor();
                break;

            default:
                erro();
        }
        nivel--;
    }


    //simple-expr1 -> term | simple-expr addop term| LAMBDA
    private void simpleExpr1() throws IOException {

        if (token.tag == Tag.OR || token.tag == '+' || token.tag == '-') {

            addop();
            term();
            simpleExpr1();

        }

    }

    //addop -> + | - | ||
    private void addop() throws IOException {
        imprime("Addop");
        nivel++;
        switch (token.tag) {

            case '+':
                eat('+');
                break;

            case '-':
                eat('-');
                break;

            case Tag.OR:
                eat(Tag.OR);
                break;

            default:
                erro();

        }
        nivel--;
    }

    //factor ->  identifier | constant | '(' expression ')'
    private void factor() throws IOException {
        imprime("Fator");
        nivel++;
        switch (token.tag) {

            case Tag.IDENTIFIER:
                identifier();
                break;

            case Tag.INT_CONST:
            case Tag.STR_LITERAL:
                constant();
                break;

            case '(':
                eat('(');
                expression();
                eat(')');
                break;

            default:
                erro();

        }
        nivel--;
    }

    //expression -> simple-expr expression1
    private void expression() throws IOException {
        imprime("Expression");
        nivel++;
        switch (token.tag) {

            case Tag.IDENTIFIER:
            case Tag.INT_CONST:
            case Tag.STR_LITERAL:
            case '(':
            case '!':
            case '-':
                simpleExpr();
                expression1();
                break;

            default:
                erro();

        }
        nivel--;

    }

    //expression1 -> relop expression1 | LAMBDA
    private void expression1() throws IOException {

        if (token.tag == Tag.EQ || token.tag == Tag.LE || token.tag == Tag.GE || token.tag == Tag.NE || token.tag == '>' || token.tag == '<') {

            relop();
            expression(); //ERROR

        }
    }

    //constant -> INT_CONST | STR_LITERAL
    private void constant() throws IOException {
        imprime("Constante");
        nivel++;
        switch (token.tag) {

            case Tag.INT_CONST:

                eat(Tag.INT_CONST);
                break;

            case Tag.STR_LITERAL:

                eat(Tag.STR_LITERAL);
                break;

            default:
                erro();

        }
        nivel--;
    }

    //term1 -> mulop factor-a term1 | LAMBDA
    private void term1() throws IOException {

        if (token.tag == Tag.AND || token.tag == '*' || token.tag == '/') {

            mulop();
            factorA();
            term1();

        }

    }

    //mulop -> * | / | &&
    private void mulop() throws IOException {
        imprime("Mulop");
        nivel++;
        switch (token.tag) {

            case '*':
                eat('*');
                break;

            case '/':
                eat('/');
                break;

            case Tag.AND:
                eat(Tag.AND);
                break;

            default:
                erro();

        }
        nivel--;

    }

    //relop -> "==" | ">" | ">=" | "<" | "<=" | "!="
    private void relop() throws IOException {
        imprime("Relop");
        nivel++;
        switch (token.tag) {

            case Tag.EQ:
                eat(Tag.EQ);
                break;

            case '>':
                eat('>');
                break;

            case Tag.GE:
                eat(Tag.GE);
                break;

            case '<':
                eat('<');
                break;

            case Tag.LE:
                eat(Tag.LE);
                break;

            case Tag.NE:
                eat(Tag.NE);
                break;

            default:
                erro();

        }
        nivel--;
    }

    //if-stmt -> IF expression THEN stmt-list else-stmt END
    private void ifFunc() throws IOException {
        imprime("IfFunc");
        nivel++;
        switch (token.tag) {

            case Tag.IF:
                eat(Tag.IF);
                expression();
                eat(Tag.THEN);
                stmtList();
                elseStmt();
                eat(Tag.END);
                break;

            default:
                erro();
        }
        nivel--;

    }

    //else-stmt ->  ELSE stmt-list END | LAMBDA
    private void elseStmt() throws IOException {
        imprime("ElseStmt");
        nivel++;
        if ((token.tag)==(Tag.ELSE)) {
                eat(Tag.ELSE);
                stmtList();
        }
        nivel--;
    }

    //scanFunc:- scan "(" identifier ")"

    private void scanFunc() throws IOException {
        imprime("ScanFunc");
        nivel++;
        switch (token.tag) {

            case Tag.SCAN:
                eat(Tag.SCAN);
                eat('(');
                identifier();
                eat(')');
                break;

            default:
                erro();
        }
        nivel--;
    }

    //printFunc ->  print "(" writable ")"
    private void printFunc() throws IOException {
        imprime("PrintFunc");
        nivel++;
        switch (token.tag) {

            case Tag.PRINT:
                eat(Tag.PRINT);
                eat('(');
                writable();
                eat(')');
                break;

            default:
                erro();
        }
        nivel--;
    }

    //writable -> simple-expr  | STR_LITEARAL
    private void writable() throws IOException {
        imprime("Writable");
        nivel++;
        switch (token.tag) {

            case Tag.IDENTIFIER:
            case Tag.INT_CONST:
            case Tag.STR_LITERAL:
            case '(':
            case '!':
            case '-':
                simpleExpr();
                break;

            default:
                erro();

        }
        nivel--;

    }

    //whileFunc: -   do stmt-list stmt-sufix
    private void whileFunc() throws IOException {
        imprime("WhileFunc");
        nivel++;
        switch (token.tag) {

            case Tag.DO:
                eat(Tag.DO);
                //stmtList1();
                stmtList();
                eat(Tag.WHILE);
                expression();
                break;

            default:
                erro();

        }
        nivel--;
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        String nomeArquivo = args[0];
        Lexico lex = new Lexico(nomeArquivo);
        Sintatico sint =  new Sintatico(lex);
        sint.program();
        System.out.print("\n");
        //System.out.print(TabelaSimbolos.getTabelaSimbolo());
    }
}

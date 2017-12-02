package semantico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lexico.Lexico;
import lexico.TabelaSimbolos;
import lexico.Tag;
import lexico.Token;


public abstract class semantico {
    
    public semantico head;
    public String tipo;
    public boolean declaracao;
    public static Lexico lexico;
    public static Token token;
    public static TabelaSimbolos tabelaSimbolos = TabelaSimbolos.getInstance();
    public static List<Token> idList = new ArrayList<Token>();
    public static boolean isDecl = false;
    
    protected semantico(semantico head){
        this.head = head;
        this.tipo = "void";
        this.declaracao = false;
    }
    
    
    protected void erro() {
        System.exit(0);
    }
    
    protected void eat(int tag) throws IOException{
        
    	if (token.tag == tag) {
            
    		System.out.println("eat " + token);
            token = lexico.nextToken();
            
        } else {

            System.out.println("Erro Sintático na linha " + Lexico.numLinha + ":\n" + "Token esperado: \"" + Token.getLexema(tag) + "\"\n" + "Token encontrado: \"" + Token.lexema + "\"");
            erro();
            
        }
    	
    }
    
    public abstract void analise();
   
}
    
   

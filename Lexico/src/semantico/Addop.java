package semantico;

import Lexico.Lexico;
import Lexico.Tag;
import lexico.Token;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* addop -> + | - | OR
private void addop() throws IOException {
	
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
}*/

public class Addop extends sintatico {
    
    
    
    public Addop(semantico head){
        super(head);
    }
    
    @Override
    public void analise()  {
    	
        switch (Token.Tag) {

	    
        	case '+': {
        		try {
        			eat('+');
        		} catch (IOException ex) {
        			Logger.getLogger(Addop.class.getName()).log(Level.SEVERE, null, ex);
        		}
        	}
            
            this.tipo = head.tipo;
            break;
	    
        	case '-': {
	            try {
	                eat('-');
	            } catch (IOException ex) {
	                Logger.getLogger(Addop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
            this.tipo = head.tipo;
            break;
            
		    case Tag.OR: {
	            try {
	                eat(Tag.OR);
	            } catch (IOException ex) {
	                Logger.getLogger(Addop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
            this.tipo = "bool";
            break;
		    
		    default:
                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Operador aritmético esperado, porém nao encontrado.");
                erro();
		}
    }
    
}

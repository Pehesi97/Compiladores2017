
package semantico;

import Lexico.Lexico;
import Lexico.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* mulop -> * | / | AND
private void mulop() throws IOException {
	
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
	
}
*/

public class Mulop extends semantico.semantico {
    
    public Mulop(semantico.semantico head) {
        super(head);
    }
    
    @Override
    public void analise() {
        
    	switch(token.tag) {
	    
	    	case '*': {
	            
	    		try {
	                eat('*');
	            } catch (IOException ex) {
	                Logger.getLogger(Mulop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
            this.tipo = head.tipo;
			break;
		    
	    	case '/': {
	            
	    		try {
	                eat('/');
	            } catch (IOException ex) {
	                Logger.getLogger(Mulop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
            this.tipo = head.tipo;
			break;
			
		    case Tag.AND: {
	            
		    	try {
	                eat(Tag.AND);
	            } catch (IOException ex) {
	                Logger.getLogger(Mulop.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
            this.tipo = "bool";
			break;
		    
		    default:
	                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n"+ "Operador aritmético esperado, porém nao encontrado.");
	                erro();
	}
    }
}

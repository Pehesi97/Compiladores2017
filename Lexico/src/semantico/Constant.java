
package semantico;

import Lexico.Lexico;
import Lexico.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* constant -> INT_CONST | LIT
private void constant() throws IOException {
	
	switch (token.tag) {
	    
		case Tag.INT_CONST:
			
			eat(Tag.INT_CONST);
			break;
	    
		case Tag.LIT:
			
			eat(Tag.LIT);
			break;
			
	    default:
	    	erro();
	    	
	}
}
*/

public class Constant extends semantico.semantico {
    
    public Constant(semantico.semantico head){
        super(head);
    }
    
    @Override
    public void analise() {
        
    	switch (token.tag) {
		    case Tag.INT_CONST: {
	            
		    	try {
	                eat(Tag.INT_CONST);
	            } catch (IOException ex) {
	                Logger.getLogger(Constant.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
            this.tipo = "inteiro";
			break;
		    
		    case Tag.LIT: {
	            
		    	try {
	                eat(Tag.LIT);
	            } catch (IOException ex) {
	                Logger.getLogger(Constant.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
            this.tipo = "literal";
			break;
	
		    default:
	                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Constante númerica esperada porém não encontrada.");
			erro();
    	}
    }
    
}

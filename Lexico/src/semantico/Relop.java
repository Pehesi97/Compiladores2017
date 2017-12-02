
package semantico;

import Lexico.Lexico;
import Lexico.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*relop -> = | > | >= | < | <= | <>
private void relop() throws IOException {
	
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
	    
	    case Tag.DI:
			eat(Tag.DI);
			break;
	    
	    default:
	    	erro();
	
   }
}
*/

public class Relop extends semantico {
    
    public Relop(semantico head) {
        super(head);
    }
    
    
    @Override
    public void analise() {
        
    	switch(token.tag){
	    case Tag.EQ: {
            
	    	try {
                eat(Tag.EQ);
            } catch (IOException ex) {
                Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.tipo = "bool";
		break;
		
	    case '>': {
            
	    	try {
                eat('>');
            } catch (IOException ex) {
                Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.tipo = "bool";
		break;
		
	    case Tag.GE: {
            
	    	try {
                eat(Tag.GE);
            } catch (IOException ex) {
                Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.tipo = "bool";
		break;
		
	    case '<': {
            
	    	try {
                eat('<');
            } catch (IOException ex) {
                Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.tipo = "bool";
		break;
		
	    case Tag.LE: {
            
	    	try {
                eat(Tag.LE);
            } catch (IOException ex) {
                Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.tipo = "bool";
		break;
		
	    case Tag.DI: {
            
	    	try {
                eat(Tag.DI);
            } catch (IOException ex) {
                Logger.getLogger(Relop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.tipo = "bool";
		break;
		
	    default:
                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Operador relacional esperado, porém não encontrado.");
                erro();
                
    	}
    }
}

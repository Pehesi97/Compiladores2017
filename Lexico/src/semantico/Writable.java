
package semantico;

import Lexico.Lexico;
import Lexico.Tag;

/*writable -> simple-expr
private void writable() throws IOException {
    
	switch (token.tag) { 
        	    	
		case Tag.ID:
        case Tag.INT_CONST:
        case Tag.LIT:
        case '(':
        case Tag.NOT:
        case '-':
            simpleExpr();
            break;
        
        default:
            erro();
            
    }
	
}
*/

public class Writable extends semantico.semantico {
    
    SimpleExpr simpleExpr;
    
    public Writable (semantico.semantico head) {
        super(head);
    }
    
    @Override
    public void analise() {
        
    	switch (token.tag) {
            
    		case Tag.ID:
            case Tag.INT_CONST:
            case Tag.LIT:
            case '(':
            case Tag.NOT:
            case '-':
                simpleExpr = new SimpleExpr(this);
                simpleExpr.analise();
                break;
          
            default:
                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Writable esperado não encontrado.");
                erro();
        }
    }
    
}

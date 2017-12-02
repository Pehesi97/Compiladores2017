
package semantico;

import Lexico.Lexico;
import Lexico.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*factor-a -> factor | NOT factor | - factor
private void factorA() throws IOException {
	
   switch (token.tag) { 
        
   		case Tag.ID:
        case Tag.INT_CONST:
        case Tag.LIT:
        case '(':
            factor();
            break;                
       
        case Tag.NOT:
            eat(Tag.NOT);
            factor();
            break;   
            
        case '-':
            eat('-');
            factor();
            break;                
        
        default:
            erro();
    }
}
*/

public class FactorA extends semantico.semantico {
    
    Factor factor;
    
    public FactorA(semantico.semantico head){
        super(head);
    }
    
    @Override
    public void analise() {
        
    	switch (token.tag) {
            
    		case Tag.ID:
            case Tag.INT_CONST:
            case Tag.LIT:
            case '(':
                factor = new Factor(this);
                factor.analise();
                this.tipo = factor.tipo;
                break;                
            
            case Tag.NOT: {
		        try {
		            eat( Tag.NOT);
		        } catch (IOException ex) {
		            Logger.getLogger(FactorA.class.getName()).log(Level.SEVERE, null, ex);
		        }
		    }
	            factor = new Factor(this);
	            factor.analise();
	            if (!factor.tipo.equals("bool")) {
	                System.out.println("Erro Semântico na linha " + Lexico.numLinha + ":\n" + "Operador booleano esperado.");
	                erro();
	            }
	            this.tipo = factor.tipo;
	            break;
        
            case '-': {
		            try {
		                eat('-');
		            } catch (IOException ex) {
		                Logger.getLogger(FactorA.class.getName()).log(Level.SEVERE, null, ex);
		            }
    		}
	            factor = new Factor(this);
	            factor.analise();
	            if ((!factor.tipo.equals("inteiro"))&&(!factor.tipo.equals("literal"))) {
	                System.out.println("Erro Semântico na linha " + Lexico.numLinha + ":\n" + "Operador númerico esperado.");
	                erro();
	            }
	            this.tipo = factor.tipo;
	            break;
            
            default:
                System.out.println("Erro sintatico na linha " + Lexico.numLinha + ":\n" + "Expressao esperada nao encontrada.");
                erro();
        }
    }
    
}

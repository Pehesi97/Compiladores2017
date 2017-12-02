
package semantico;

import Lexico.Lexico;
import Lexico.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*factor -> identifier | constant | ( expression )
private void factor() throws IOException {

   switch (token.tag) {

	   case Tag.ID:
		   identifier();
		   break;
		   
	   case Tag.INT_CONST:
	   case Tag.LIT:
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
   
}
*/

public class Factor extends semantico.semantico {
    
    Identifier identifier;
    Constant constant;
    Expression expression;
    
    public Factor(semantico.semantico head) {
        super(head);
    }
    
    @Override
    public void analise() {
        
    	switch (token.tag) {
            case Tag.ID:
            	identifier = new Identifier(this);
                identifier.analise();
                this.tipo = identifier.tipo;
                break;
            
            case Tag.INT_CONST:
            case Tag.LIT:
            	constant = new Constant(this);
                constant.analise();
                this.tipo = constant.tipo;
                break;
           
            case '(': {
            	
            	try {
            		eat('(');
	            } catch (IOException ex) {
	                Logger.getLogger(Factor.class.getName()).log(Level.SEVERE, null, ex);
	            }
            	
	        }
            
            expression = new Expression(this);
            expression.analise();
            this.tipo = expression.tipo;
            
            {
            	try {
	                eat(')');
	            } catch (IOException ex) {
	                Logger.getLogger(Factor.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
            break;                                                                                                                                
            
            default:
                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Expressão esperada não encontrada.");
                erro();
        }
    }
    
}

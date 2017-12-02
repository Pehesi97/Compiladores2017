
package semantico;

import Lexico.Lexico;
import Lexico.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Util.Util;

/*assign-stmt -> identifier := simple_expr
private void assignStmt() throws IOException {
    
	switch (token.tag) {
        
		case Tag.ID:
            
			identifier();
            eat(Tag.ATB);
            simpleExpr();
            break;
        
		default:
            erro();
    }
	
}
*/


public class AssignStmt extends semantico {
    
    Identifier identifier;
    SimpleExpr simpleExpr;
    
    public AssignStmt(semantico head){
        super(head);
    }
    
    @Override
    public void analise() {
        
    	switch (token.tag) {
            
    		case Tag.ID:
                
            	identifier = new Identifier(this);
                identifier.analise();
		        
                {
		            try {
		                eat(Tag.ATB);
		            } catch (IOException ex) {
		                Logger.getLogger(AssignStmt.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
                
                simpleExpr = new SimpleExpr(this);
                simpleExpr.analise();
                
                if (!Util.canAssign(identifier.tipo, simpleExpr.tipo)) {
                    
                	System.out.println("Erro semântico na linha " + Lexico.numLinha + ":\n" + "Tipos incompatíveis.");
                    erro();
                }
                
                break;
            
    		default:
                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Atribuição esperada, porém nao encontrada.");
                erro();
                
        }
    }
}

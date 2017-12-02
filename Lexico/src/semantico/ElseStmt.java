
package semantico;

import Lexico.Lexico;
import Lexico.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*else-stmt -> END | ELSE stmt-list END
private void elseStmt() throws IOException {
    
	switch (token.tag) {
        
		case Tag.END:
            eat(Tag.END);
            break;
		
		case Tag.ELSE:
			eat(Tag.ELSE);
			stmtList();
            eat(Tag.END);
            break;
            
        default:
            erro();
            
    }
}
*/

public class ElseStmt extends semantico.semantico {
    
	StmtList stmtList;
    
    public ElseStmt(semantico.semantico head) {
        super(head);
    }
    
    @Override
    public void analise() {
        
    	switch (token.tag) {
    	
    		case Tag.END: 
    			{
		            try {
		                eat(Tag.IF);
		            } catch (IOException ex) {
		                Logger.getLogger(ElseStmt.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
                
		        break;
    		
    		case Tag.ELSE:
	    		{
		            try {
		                eat(Tag.ELSE);
		            } catch (IOException ex) {
		                Logger.getLogger(ElseStmt.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }   
	    		
	    		stmtList = new StmtList(this);
	    		stmtList.analise();
		        
	            {
		            try {
		                eat(Tag.END);
		            } catch (IOException ex) {
		                Logger.getLogger(ElseStmt.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
                break;
                
            default:
                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Comando 'ELSE' esperado, porém não encontrado.");
                erro();
        }
    }
    
}

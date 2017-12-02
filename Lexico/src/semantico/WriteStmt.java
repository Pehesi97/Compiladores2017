
package semantico;

import Lexico.Lexico;
import Lexico.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*write-stmt -> OUT ( writable )
private void writeStmt() throws IOException {
    
	switch (token.tag) {
        
		case Tag.OUT:
            eat(Tag.OUT);
            eat('(');
            writable();
            eat(')');
            break;
        
		default:
            erro();
    }
	
}
*/

public class WriteStmt extends semantico.semantico {
    
    Writable writable;
    
    public WriteStmt(semantico.semantico head){
        super(head);
    }
    
    @Override
    public void analise() {
        
    	switch (token.tag) {
            case Tag.OUT:
		        {
		            try {
		                eat(Tag.OUT);
		            } catch (IOException ex) {
		                Logger.getLogger(WriteStmt.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
		        
		        {
		            try {
		                eat('(');
		            } catch (IOException ex) {
		                Logger.getLogger(WriteStmt.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
                
		        writable = new Writable(this);
                writable.analise();
		        
                {
		            try {
		                eat(')');
		            } catch (IOException ex) {
		                Logger.getLogger(WriteStmt.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
                break;
            
            default:
                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Comando de escrita esperado mas não encontrado.");
                erro();
        }
    	
    }
    
}

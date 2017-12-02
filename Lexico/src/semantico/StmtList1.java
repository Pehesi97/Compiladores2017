
package semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Lexico.Tag;

/*stmt-list1 -> stm ; stmt-list1 | LAMBDA
private void stmtList1() throws IOException {
    
	if (token.tag == Tag.ID || token.tag == Tag.IF || token.tag == Tag.DO || token.tag == Tag.IN || token.tag == Tag.OUT) {
        
        stmt();
        eat(';');
        stmtList1();
        
    }
	
}
*/

public class StmtList1 extends semantico.semantico {
    
    Stmt stmt;
    StmtList1 stmtList1;
    
    public StmtList1(semantico.semantico head) {
        super(head);
    }
    
    @Override
    public void analise(){
        
    	if (token.tag == Tag.ID || token.tag == Tag.IF || token.tag == Tag.DO || token.tag == Tag.IN || token.tag == Tag.OUT) {
            
    		stmt = new Stmt(this);
            stmt.analise();
    		
    		try {
                eat(';');
            } catch (IOException ex) {
                Logger.getLogger(StmtList1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            stmtList1 = new StmtList1(this);
            stmtList1.analise();
            
        }
    	
    }
    
}

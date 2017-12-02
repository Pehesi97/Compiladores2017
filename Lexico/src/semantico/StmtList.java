
package semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Lexico.Lexico;
import Lexico.Tag;

/*stmt-list -> stm ; stmt-list1
private void stmtList() throws IOException {
    
	switch (token.tag) {
        
		case Tag.ID:
        case Tag.IF:
        case Tag.DO:
        case Tag.IN:
        case Tag.OUT:
            stmt();
            eat(';');
            stmtList1();
            break;
        
        default:
            erro();
    }
	
}
*/

public class StmtList extends semantico.semantico {
    
    Stmt stmt;
    StmtList1 stmtList1;
    
    public StmtList(semantico.semantico head){
        super(head);
    }
    
    @Override
    public void analise() {
        
    	switch (token.tag) {
	        case Tag.ID:
	        case Tag.IF:
	        case Tag.DO:
	        case Tag.IN:
	        case Tag.OUT:
                stmt = new Stmt(this);
                stmt.analise();
                
                try {
                    eat(';');
                } catch (IOException ex) {
                    Logger.getLogger(StmtList.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                stmtList1 = new StmtList1(this);
                stmtList1.analise();
                break;
            
	        default:
                System.out.println("Erro sintatico na linha " + Lexico.numLinha + ":\n" + "Comando esperado não encontrado.");
                erro();
        }
    }
}

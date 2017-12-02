
package semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Lexico.Lexico;
import Lexico.Tag;

/*if-stmt -> IF expression THEN stmt-list else-stmt
private void ifStmt() throws IOException {
    
	switch (token.tag) {
        
		case Tag.IF:
            eat(Tag.IF);
            expression();
            eat(Tag.THEN);
            stmtList();
            elseStmt();
            break;
        
		default:
            erro();
    }
	
}
*/

public class IfStmt extends semantico.semantico {
    
	Expression expression;
	StmtList stmtList;
	ElseStmt elseStmt;
    
    public IfStmt(semantico.semantico head){
        super(head);
    }
    
    @Override
    public void analise() {
    	
    	switch (token.tag) {
    	
			case Tag.IF:
	    		{
		            try {
		                eat(Tag.IF);
		            } catch (IOException ex) {
		                Logger.getLogger(ElseStmt.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }   
	    		
	    		expression = new Expression(this);
	    		expression.analise();
		        
	            {
		            try {
		                eat(Tag.THEN);
		            } catch (IOException ex) {
		                Logger.getLogger(ElseStmt.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
	            
	            stmtList = new StmtList(this);
	            stmtList.analise();
	            
	            elseStmt = new ElseStmt(this);
	            elseStmt.analise();
	    		
	            break;
	            
	        default:
	            System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Comando 'IF' esperado, porém não encontrado.");
	            erro();
	    }
    	
    }
    
}


package semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Lexico.Lexico;
import Lexico.Tag;

/*do-stmt -> DO stmt-list WHILE expression
private void doStmt() throws IOException {
    
	switch (token.tag) {
        
		case Tag.DO:
			eat(Tag.DO);
			stmtList();
			eat(Tag.WHILE);
            expression();
            break;
            
        default:
            erro();
            
    }
}
*/

public class DoStmt extends semantico.semantico {
    
	Expression expression;
    StmtList stmtList;
    
    public DoStmt(semantico.semantico head){
        super(head);
    }
    
    @Override
    public void analise() {
        
    	switch(token.tag) {
            
    	case Tag.DO:
    		
    		{
	    		try {
					eat(Tag.DO);
				} catch (IOException ex) {
					Logger.getLogger(DoStmt.class.getName()).log(Level.SEVERE, null, ex);
				}
    		}
    		
    		stmtList = new StmtList(this);
    		stmtList.analise();
    		
    		{
	    		try {
					eat(Tag.WHILE);
				} catch (IOException ex) {
					Logger.getLogger(DoStmt.class.getName()).log(Level.SEVERE, null, ex);
				}
    		}
    		
    		expression = new Expression(this);
    		expression.analise();
    		
            break;
            
    		default:
                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Comando de laço DO esperado, porém não encontrado.");
                erro();
        }
    }
    
}

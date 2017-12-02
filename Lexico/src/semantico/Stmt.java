
package semantico;

import Lexico.Lexico;
import Lexico.Tag;

/*stmt -> assign-stmt | if-stmt | do-stmt | read-stmt | write-stmt
private void stmt() throws IOException {
    
	switch (token.tag) {
        
		case Tag.ID:
            assignStmt();
            break;
            
        case Tag.IF:
            ifStmt();
            break;
            
        case Tag.DO:
            doStmt();
            break;
        
        case Tag.IN:
            readStmt();
            break;
            
        case Tag.OUT:
            writeStmt();
            break;
            
        default:
            erro();
            
    }
	
}  
*/

public class Stmt extends semantico.semantico {
    
    AssignStmt assignStmt;
    IfStmt ifStmt;
    DoStmt doStmt;
    ReadStmt readStmt;
    WriteStmt writeStmt;

    public Stmt(semantico.semantico head) {
        super(head);
    }
    
    @Override
    public void analise() {
        
    	switch (token.tag) {
            
    		case Tag.ID:
                assignStmt = new AssignStmt(this);
                assignStmt.analise();
                break;
                
            case Tag.IF:
                ifStmt = new IfStmt(this);
                ifStmt.analise();
                break;
            
            case Tag.DO:
                doStmt = new DoStmt(this);
                doStmt.analise();
                break;
     
            case Tag.IN:
                readStmt = new ReadStmt(this);
                readStmt.analise();
                break;
            
            case Tag.OUT:
                writeStmt = new WriteStmt(this);
                writeStmt.analise();
                break;
            
            default:
                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Comando esperado, porém não encontrado.");
                erro();
                
        }
    }
    
}



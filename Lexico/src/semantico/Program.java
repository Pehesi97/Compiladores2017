
package semantico;

import Lexico.Lexico;
import Lexico.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



/*program -> VAR decl-list BEGIN stmt-list END | BEGIN stmt-list END
private void program() throws IOException {
    
	switch (token.tag) {
        
		case Tag.VAR:
            eat(Tag.VAR);
            declList();
            eat(Tag.BEGIN);
            stmtList();
            eat(Tag.END);
            break;     
         
		case Tag.BEGIN:
            eat(Tag.BEGIN);
            stmtList();
            eat(Tag.END);
            break;     
            
        default:
            erro();
            
    }
	
}   
*/

public class Program extends semantico.semantico {
    
    Identifier identifier;
    DeclList declList;
    StmtList stmtList;
    
    public Program(semantico.semantico head){
        super(head);
    }
    
    @Override
    public void analise() {
        
    	switch(token.tag) {
            
    		case Tag.VAR:
		        {
		            try {
		                eat(Tag.VAR);
		            } catch (IOException ex) {
		                Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
		        
                this.tipo = "var";
                declList = new DeclList(this);
                declList.analise();
                
                {
		            try {
		                eat(Tag.BEGIN);
		            } catch (IOException ex) {
		                Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
                
                stmtList = new StmtList(this);
                stmtList.analise();
                
                {
		            try {
		                eat(Tag.END);
		            } catch (IOException ex) {
		                Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
                break;
    		
    		case Tag.BEGIN:
	    		{
		            try {
		                eat(Tag.BEGIN);
		            } catch (IOException ex) {
		                Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
	            
	            stmtList = new StmtList(this);
	            stmtList.analise();
	            
	            {
		            try {
		                eat(Tag.END);
		            } catch (IOException ex) {
		                Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
    			break;
                
            default:
                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Declaração de inicialização do programa esperada, porém não encontrada.");
                erro();            
        }
    }
}


package semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Lexico.Lexico;
import Lexico.Tag;

/*decl-list	-> decl ; decl-list
private void declList() throws IOException {
    
	switch (token.tag) {
    
		case Tag.ID:
			decl();
            eat(';');
            declList1();
            break;
        
        default:
            erro();
        
	}
	
}
*/

public class DeclList extends semantico.semantico {
    
    Decl decl;
    DeclList1 declList1;
    
    public DeclList(semantico.semantico head){
        super(head);
    }
    
    @Override
    public void analise() {
        
    	switch (token.tag) {
            
    		case Tag.ID:
                decl = new Decl(this);
                decl.analise();
                
                try {
                    eat(';');
                } catch (IOException ex) {
                    Logger.getLogger(DeclList.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                declList1 = new DeclList1(this);
                declList1.analise();
                break;
            
    		default:
                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Comando de atribução esperado, porém nao encontrado.");
                erro();
        }
    }
}

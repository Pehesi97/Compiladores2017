package semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Lexico.Tag;

/*decl-list1 -> decl ; decl-list | LAMBDA
private void declList1() throws IOException {
    
	if (token.tag == Tag.ID) {
		
		decl();
        eat(';');
        declList1();
		
	}
	
}
*/

public class DeclList1 extends semantico.semantico {
    
    Decl decl;
    DeclList1 declList1;
    
    public DeclList1(semantico.semantico head){
        super(head);
    }
    
    @Override
    public void analise() {
    	
    	if (token.tag == Tag.ID) {
            
    		decl = new Decl(this);
            decl.analise();
    		
    		try {
                eat(';');
            } catch (IOException ex) {
                Logger.getLogger(DeclList1.class.getName()).log(Level.SEVERE, null, ex);
            }
    		
            declList1 = new DeclList1(this);
            declList1.analise();
            
        }
    }
    
}




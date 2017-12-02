package semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*ident-list1 -> , identifier ident-list1 | LAMBDA
private void identList1() throws IOException {
    
	if (token.tag == ',') {
        
		eat(',');
		identifier();
        identList1();
        
    }
	
}
*/

public class IdentList1 extends semantico.semantico {
    
    Identifier identifier;
    IdentList1 identList1;
    
    public IdentList1(semantico.semantico head){
        super(head);
    }
    
    @Override
    public void analise() {
        
    	if (token.tag == ',') {
            
    		this.tipo = head.tipo;
            
    		try {
                eat(',');
            } catch (IOException ex) {
                Logger.getLogger(IdentList1.class.getName()).log(Level.SEVERE, null, ex);
            }
    		
            identifier = new Identifier(this);
            identifier.declaracao = true;
            identifier.analise();
            
            identList1 = new IdentList1(this);
            identList1.analise();
            
        }
    }
        
}


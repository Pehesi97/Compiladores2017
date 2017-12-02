package semantico;

import Lexico.Lexico;
import Lexico.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*type -> INTEGER | STRING
private void type() throws IOException {
    
	switch (token.tag) {
        
		case Tag.INT:
            eat(Tag.INT);
            break;
        
		case Tag.STRING:
            eat(Tag.STRING);
            break;
        
		default:
            erro();
    }
	
}
*/

public class Type extends semantico.semantico {
    
    public Type(semantico.semantico head) {
        super(head);
    }
    
    @Override
    public void analise() {
        
    	switch (token.tag) {
            
        	case Tag.INT:
		        {
		            try {
		                eat(Tag.INT);
		            } catch (IOException ex) {
		                Logger.getLogger(Type.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
                this.tipo = "inteiro";
                break;
                
            case Tag.STRING:
		        {
		            try {
		                eat(Tag.STRING);
		            } catch (IOException ex) {
		                Logger.getLogger(Type.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
                this.tipo = "literal";
                break;
                
            default:
                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Tipo esperado não encontrado");
                erro();
        }
    }
}

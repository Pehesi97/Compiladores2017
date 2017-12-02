
package semantico;

import Lexico.Lexico;
import Lexico.Tag;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*read-stmt	-> IN ( identifier )
private void readStmt() throws IOException {
    
	switch (token.tag) {
        
		case Tag.IN:
            eat(Tag.IN);
            eat('(');
            identifier();
            eat(')');
            break;
        
		default:
            erro();
    }
	
}
 */

public class ReadStmt extends semantico.semantico {

	Identifier identifier;

	public ReadStmt (semantico.semantico head) {
		super(head);
	}

	@Override
	public void analise() {

		switch (token.tag) {

			case Tag.IN: {
				
				try {
					eat(Tag.IN);
				} catch (IOException ex) {
					Logger.getLogger(ReadStmt.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			
			{
				try {
					eat('(');
				} catch (IOException ex) {
					Logger.getLogger(ReadStmt.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			
			identifier = new Identifier(this);
			identifier.analise();
			
			{
				try {
					eat(')');
				} catch (IOException ex) {
					Logger.getLogger(ReadStmt.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			
			break;
			
			default:
				System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Comando de entrada esperado mas não encontrado.");
				erro();
		}
		
	}

}

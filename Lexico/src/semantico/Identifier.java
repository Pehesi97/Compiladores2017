
package semantico;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Lexico.Lexico;
import Lexico.Tag;
import Lexico.Token;

/*identifier -> id
private void identifier() throws IOException {

	switch (token.tag) {

		case Tag.ID:

			eat(Tag.ID);
            break;

        default:
            erro();

    }
}
 */

public class Identifier extends semantico.semantico {
	
	public Identifier(semantico.semantico head) {
		super(head);
	}

	@Override
	public void analise() {

		switch(token.tag) {

			case Tag.ID:
				
				Token tok = tabelaSimbolos.get(token.lexema);
				
				if (isDecl) {
					
					this.tipo = head.tipo;
					
					if (tok != null) {
						
						tok = new Token(token.lexema, Tag.ID);
						tok.tipo = this.tipo;
						tabelaSimbolos.put(tok);
						idList.add(tok);
						
					}
					
				} else { 
					
					if (!tok.declaracao) {
				
						System.out.println("Erro semântico na linha " + Lexico.numLinha + ":\n" + "Utilização de identificador não definido '" + token.lexema + "'.");
						erro();
					}
					
					this.tipo = tok.tipo;
					
				} 
				
				{
					try {
						eat(Tag.ID);
					} catch (IOException ex) {
						Logger.getLogger(Identifier.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
				break;	
				
			default:
				System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Identificador esperado, porém não encontrada.");
				erro();

		}
	}	

}

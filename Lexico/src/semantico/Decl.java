
package semantico;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Lexico.Lexico;
import Lexico.Tag;
import Lexico.Token;

/*
decl -> ident-list IS type
private void decl() throws IOException {
    
	switch (token.tag) {
        
		case Tag.ID:
			identList();
            eat(Tag.IS);
            type();
            break;
        
        default:
            erro();
            
    }
	
}
*/

public class Decl extends semantico.semantico {
    
    Type type;
    IdentList identList;
    
    public Decl(semantico.semantico head){
        super(head);
    }
    
	@Override
    public void analise() {
        
    	switch (token.tag) {
            
    		case Tag.ID:
    			isDecl = true;
    			identList = new IdentList(this);
                identList.analise();
                
                {
		            try {
		                eat(Tag.IS);
		            } catch (IOException ex) {
		                Logger.getLogger(Decl.class.getName()).log(Level.SEVERE, null, ex);
		            }
		        }
                
                type = new Type(this);
                type.analise();
                
                setType(idList, type);
                idList.clear();
                isDecl = false;
                break;
                
            default:
                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Declaração de variaveis esperada, porém não encontrada.");
                erro();
        }
    }
	
	public void setType(List<Token> idList, Type type){
		
		for (Token token : idList){
			token.tipo = type.tipo;
			token.declaracao = true;
		}
		
	}
    
}

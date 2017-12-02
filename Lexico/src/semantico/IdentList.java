package semantico;

import Lexico.Lexico;
import Lexico.Tag;

/*
ident-list -> identifier ident-list1
private void identList() throws IOException {
    
	switch (token.tag) {
        
		case Tag.ID:
			identifier();
            identList1();
            break;
            
        default:
            erro();
            
    }
	
}
*/

public class IdentList extends semantico.semantico {
    
    Identifier identifier;
    IdentList1 identList1;
    
    public IdentList(semantico.semantico head){
        super(head);
    }
    
    @Override
    public void analise() {
        
    	switch (token.tag) {
            
    		case Tag.ID:
                this.tipo = head.tipo;
                
                identifier = new Identifier(this);
                identifier.declaracao = true;
                identifier.analise();
                
                identList1 = new IdentList1(this);
                identList1.analise();
                
                break;
            
    		default:
                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Lista de identificadores esperada, porém não encontrada.");
                erro();
        }
    }
    	
}


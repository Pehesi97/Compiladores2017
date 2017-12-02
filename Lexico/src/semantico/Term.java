package semantico;

import Lexico.Lexico;
import Lexico.Tag;
import Util.Util;

/*term -> factor-a term1
private void term() throws IOException {
	
	switch (token.tag) {
            
		case Tag.ID:			
        case Tag.INT_CONST:
        case Tag.LIT:
        case '(':               
        case Tag.NOT:
        case '-':
            factorA();
            term1();
            break;
            
        default:
            erro();
            
	}
	
}
 */
public class Term extends semantico.semantico {

    FactorA factorA;
    Term1 term1;

    public Term(semantico.semantico head) {
        super(head);
    }

    @Override
    public void analise() {
       
    	switch (token.tag) {
            
        	case Tag.ID:
            case Tag.INT_CONST:
            case Tag.LIT:
            case '(':
            case Tag.NOT:
            case '-':
                factorA = new FactorA(this);
                factorA.analise();
                this.tipo = factorA.tipo;
                term1 = new Term1(this);
                term1.analise();
                
                if (!term1.tipo.equals("void")) {
                    
                	if (!Util.isNumeric(factorA.tipo) || !Util.isNumeric(term1.tipo)) {
                        
                		if (!factorA.tipo.equals(term1.tipo)) {
                        
                            System.out.println("Erro semântico na linha " + Lexico.numLinha + ":\n" + "Operador incompaível com o tipo do operando.");
                            erro();
                        }
                    }
                }
                
                if (Util.isNumeric(term1.tipo) && Util.isNumeric(factorA.tipo)) {
                    this.tipo = Util.getNumericType(factorA.tipo, term1.tipo);
                } else {
                    this.tipo = term1.tipo;
                } 
                
                break;
                
            default:
                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n"+ "Expressão esperada não encontrada.");
                erro();
        }
    }

}

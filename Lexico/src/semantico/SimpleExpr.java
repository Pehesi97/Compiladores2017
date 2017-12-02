package semantico;

import Lexico.Lexico;
import Lexico.Tag;
import Util.Util;

/*simple-expr -> term simple-expr1
private void simpleExpr() throws IOException {
	
	switch (token.tag) {
	    
		case Tag.ID:
        case Tag.INT_CONST:
        case Tag.LIT:
        case '(':
        case Tag.NOT:
        case '-':
			term();
			simpleExpr1();
            break;
	    
        default:
        	erro();
	
	}

}
 */
public class SimpleExpr extends semantico.semantico {

    Term term;
    SimpleExpr1 simpleExpr1;

    public SimpleExpr(semantico.semantico head) {
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
                
            	term = new Term(this);
                term.analise();
                this.tipo = term.tipo;
                simpleExpr1 = new SimpleExpr1(this);
                simpleExpr1.analise();
                
                if (!simpleExpr1.tipo.equals("void")) {
                    
                	if (!Util.isNumeric(term.tipo) || !Util.isNumeric(simpleExpr1.tipo)) {
                        
                		if (!term.tipo.equals(simpleExpr1.tipo)) {
                            System.out.println("Erro semantico na linha " + Lexico.numLinha + ":\n" + "Operador incompatível com tipo do operando.");
                            erro();
                        }
                    }
                }
                
                if (Util.isNumeric(term.tipo) && Util.isNumeric(simpleExpr1.tipo)) {
                    this.tipo = Util.getNumericType(term.tipo, simpleExpr1.tipo);
                } else {
                    this.tipo = simpleExpr1.tipo;
                }
                
                break;
            
            default:
                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Expressão esperada não encontrada.");
                erro();
        }
    }

}

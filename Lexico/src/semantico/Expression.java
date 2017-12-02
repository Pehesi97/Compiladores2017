
package semantico;

import Lexico.Lexico;
import Lexico.Tag;

/*
expression -> simple-expr expression1
private void expression() throws IOException {
    
	switch (token.tag) {
        
		case Tag.ID:
        case Tag.INT_CONST:
        case Tag.LIT:
        case '(':
        case Tag.NOT:
        case '-':
            simpleExpr();
            expression1();
            break;
            
        default:
            erro();
            
    }
	
}
*/

public class Expression extends semantico.semantico {
    
    SimpleExpr simpleExpr;
    Expression1 expression1;
    
    public Expression (semantico.semantico head) {
        super(head);
    }
    
    @Override
    public void analise() {
        
    	switch (token.tag) {
            
    		case Tag.ID:
            case Tag.INT_CONST:
            case Tag.LIT:
            case '(':
            case '!':
            case '-':
                
            	simpleExpr = new SimpleExpr(this);
                simpleExpr.analise();
                
                expression1 = new Expression1(this);
                expression1.analise();
                
                if (!expression1.tipo.equals("void")) {
                    
                	if (!simpleExpr.tipo.equals(expression1.tipo)) {
                        System.out.println("Erro semântico na linha " + Lexico.numLinha + ":\n" + "Operador com tipo incompatível com o operando. ");
                        erro();
                    }
                    
                	this.tipo = "bool";
                
                } else {
                    this.tipo = simpleExpr.tipo;
                }
                break;
            
            default:
                System.out.println("Erro sintático na linha " + Lexico.numLinha + ":\n" + "Expressão esperada não encontrada.");
                erro();
        }
    }
    
}

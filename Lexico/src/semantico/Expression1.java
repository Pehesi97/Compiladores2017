
package semantico;

import Lexico.Lexico;
import Lexico.Tag;

/*expression1 -> relop expression | LAMBDA
private void expression1() throws IOException {
    
	if (token.tag == Tag.EQ || token.tag == Tag.LE || token.tag == Tag.GE || token.tag == Tag.DI || token.tag == '>' || token.tag == '<' ) {
        
		relop();
		expression(); 
        
    }
}
*/

public class Expression1 extends semantico.semantico {
    
    Relop relop;
    Expression expression;
    
    public Expression1(semantico.semantico head){
        super(head);
    }
    
    @Override
    public void analise() {
        
    	if (token.tag == Tag.EQ || token.tag == Tag.LE || token.tag == Tag.GE || token.tag == Tag.DI || token.tag == '>' || token.tag == '<' ) {
            
        	relop = new Relop(this);
            relop.analise();
            
            expression = new Expression(this);
            expression.analise();
            
            if ((!expression.tipo.equals("inteiro")) && (!expression.tipo.equals("literal"))) {
                
            	System.out.println("Erro semântico na linha " + Lexico.numLinha + ":\n" + "Operador incompatível com o tipo do operando.");
                erro();
            }
            
            this.tipo = expression.tipo;
            
        }
    }
    
}

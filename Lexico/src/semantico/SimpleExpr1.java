package semantico;

import Lexico.Lexico;
import Lexico.Tag;
import Util.Util;

/*simple-expr1 -> addop term simple-expr1 | LAMBDA
private void simpleExpr1() throws IOException {
	
	if (token.tag == Tag.OR || token.tag == '+' || token.tag == '-') {
	    
		addop();
	    term();
	    simpleExpr1();
	    
	}
	
} 
 */
public class SimpleExpr1 extends semantico.semantico {

    Addop addop;
    Term term;
    SimpleExpr1 simpleExpr1;

    public SimpleExpr1(semantico.semantico head) {
        
    	super(head);
        this.tipo = head.tipo;
    }

    @Override
    public void analise() {
        
    	if (token.tag == Tag.OR || token.tag == '+' || token.tag == '-') {
            
    		addop = new Addop(this);
            addop.analise();
            this.tipo = addop.tipo;
            term = new Term(this);
            term.analise();
            
            if (!Util.isNumeric(addop.tipo) || !Util.isNumeric(term.tipo)) {
               
            	if (!addop.tipo.equals(term.tipo)) {
                
            		System.out.println("Erro semântico na linha " + Lexico.numLinha + ":\n"  + "Operador incompatível com o tipo do operando.");
                    erro();
                    
                }
            }
            
            this.tipo = Util.getNumericType(addop.tipo, term.tipo);
            simpleExpr1 = new SimpleExpr1(this);
            simpleExpr1.analise();
            
            if (!simpleExpr1.tipo.equals("void")) {
                
            	if (!Util.isNumeric(addop.tipo) || !Util.isNumeric(simpleExpr1.tipo)) {
                    System.out.println("Erro semântico na linha " + Lexico.numLinha + ":\n" + "Operador incompatível com o tipo do operando.");
                
                    erro();

            	}
            	
            	
            }
            
            if (Util.isNumeric(addop.tipo)) {
                this.tipo = Util.getNumericType(term.tipo, simpleExpr1.tipo);
            } else {
                this.tipo = addop.tipo;
            } 

        }
    }

}

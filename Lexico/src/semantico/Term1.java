package semantico;

import Lexico.Lexico;
import Lexico.Tag;
import Util.Util;

/*term1 -> mulop factor-a term1 | LAMBDA
private void term1() throws IOException {

	if (token.tag == Tag.AND || token.tag == '*' || token.tag == '/') {
	   
		mulop();
	    factorA();
	    term1();
	
	}
	
}
 */
public class Term1 extends semantico.semantico {

    Mulop mulop;
    FactorA factorA;
    Term1 term1;

    public Term1(semantico.semantico head) {
        
    	super(head);
        this.tipo = head.tipo;
        
    }

    @Override
    public void analise() {
        
    	if (token.tag == Tag.AND || token.tag == '*' || token.tag == '/') {
            
    		mulop = new Mulop(this);
            mulop.analise();
            this.tipo = mulop.tipo;
            
            factorA = new FactorA(this);
            factorA.analise();
            
           if (!Util.isNumeric(mulop.tipo) || !Util.isNumeric(factorA.tipo)) {
                
            	if (!mulop.tipo.equals(factorA.tipo)) {
                
                    System.out.println("Erro semântico na linha " + Lexico.numLinha + ":\n" + "Operador incompatível com o tipo do operando.");
                    erro();
                }
            }
            
            term1 = new Term1(this);
            term1.analise();
            
            if (!term1.tipo.equals("void")) {
                
            	if (!mulop.tipo.equals(term1.tipo)) {
            		System.out.println("Erro semântico na linha " + Lexico.numLinha + ":\n" + "Operador incompaível com o tipo do operando.");
                    erro();
                }
            	
            }
            
            if (Util.isNumeric(mulop.tipo)) {
                this.tipo = Util.getNumericType(factorA.tipo, term1.tipo);
            } else {
                this.tipo = mulop.tipo;
            } 
        }
    }

}

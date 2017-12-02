
package semantico;

import Lexico.Lexico;
import Lexico.TabelaSimbolos;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException{
        
		Scanner in = new Scanner(System.in);
        System.out.print("Informe o nome do arquivo: ");
        String nomeArquivo = in.next();
        
        semantico.semantico.lexico = Lexico.getInstance(nomeArquivo);
        semantico.semantico.token = semantico.semantico.lexico.nextToken();
        semantico.semantico.tabelaSimbolos = TabelaSimbolos.getInstance();
        
        Program program = new Program(null);
        program.analise();
        
        System.out.print("\n" + semantico.semantico.tabelaSimbolos);
        System.out.print("\nAnálise Semântica realizada com sucesso!\n");
        
    }
	
}

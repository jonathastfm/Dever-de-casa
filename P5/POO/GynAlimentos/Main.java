import java.util.Scanner;



public class Main {

	public static void main(String[] args) {
		
		Funcionario obj = new Funcionario(null, null, 0, 0, 0);
		Gratificacao grat = new Gratificacao();
		
		double SalarioBruto, gratificacao;
		
		try (Scanner ler = new Scanner(System.in)) {
		
		System.out.print("Informe o seu nome: ");
		obj.setNome(ler.next());
		System.out.print("Informe a matr�cula: ");
		obj.setMatricula(ler.next());
		System.out.print("Informe a quantidade de filhos: ");
		obj.setNumeroDependentes(ler.nextInt());
		System.out.print("Informe o sal�rio base: ");
		obj.setSalarioBase(ler.nextDouble());
		System.out.print("Informe a produ��o: ");
		obj.setProducaoItens(ler.nextInt());
		
		grat.setProducao();
		grat.setAliquota();
		
		gratificacao = grat.valorGratificao(obj.getProducaoItens());
		SalarioBruto = obj.getSalarioBase() + gratificacao;
		
		System.out.println("Matr�cula: " + obj.getMatricula());
		System.out.println("Nome: " + obj.getNome());
		System.out.println("N�mero de Dependentes: " + obj.getNumeroDependentes());
		System.out.println("Sal�rio Base: " + obj.getSalarioBase());
		System.out.println("Valor da Gratifica��o: R$" + gratificacao);
		System.out.println("Valor da Bruto: R$" + SalarioBruto);
		
		

	}

}
}

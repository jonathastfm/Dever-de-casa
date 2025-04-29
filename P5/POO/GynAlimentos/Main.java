import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Funcionario obj = new Funcionario();
		Gratificacao grat = new Gratificacao();
		
		double SalarioBruto, gratificacao;
		
		try (Scanner ler = new Scanner(System.in)) {
		
		System.out.print("Informe o seu nome: ");
		obj.setNome(ler.next());
		System.out.print("Informe a matr�cula: ");
		obj.setMatricula(ler.nextInt());
		System.out.print("Informe a quantidade de filhos: ");
		obj.setNumDep(ler.nextInt());
		System.out.print("Informe o sal�rio base: ");
		obj.setSalarioBase(ler.nextDouble());
		System.out.print("Informe a produ��o: ");
		obj.setQtdProd(ler.nextInt());
		
		grat.setProducao();
		grat.setAliquota();
		
		gratificacao = grat.valorGratificao(obj.getQtdProd());
		SalarioBruto = obj.getSalarioBase() + gratificacao;
		
		System.out.println("Matr�cula: " + obj.getMatricula());
		System.out.println("Nome: " + obj.getNome());
		System.out.println("N�mero de Dependentes: " + obj.getNumDep());
		System.out.println("Sal�rio Base: " + obj.getSalarioBase());
		System.out.println("Valor da Gratifica��o: R$" + gratificacao);
		System.out.println("Valor da Bruto: R$" + SalarioBruto);
		
		

	}

}
}

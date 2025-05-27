import java.util.Scanner;



public class Main {

	public static void main(String[] args) {
		
		Funcionario obj = new Funcionario(null, null, 0, 0, 0);
		Gratificacao grat = new Gratificacao();
		
		double SalarioBruto, gratificacao;
		
		Scanner ler = new Scanner(System.in);

		try {
			System.out.print("Informe o seu nome: ");
			obj.setNome(ler.next());
		} catch (Exception e) {
			System.out.println("Erro ao ler o nome.");
		}

		try {
			System.out.print("Informe a matrícula: ");
			obj.setMatricula(ler.next());
		} catch (Exception e) {
			System.out.println("Erro ao ler a matrícula.");
		}

		try {
			System.out.print("Informe a quantidade de filhos: ");
			obj.setNumeroDependentes(ler.nextInt());
		} catch (Exception e) {
			System.out.println("Erro ao ler a quantidade de filhos.");
		}

		try {
			System.out.print("Informe o salário base: ");
			obj.setSalarioBase(ler.nextDouble());
		} catch (Exception e) {
			System.out.println("Erro ao ler o salário base.");
		}

		try {
			System.out.print("Informe a produção: ");
			obj.setProducaoItens(ler.nextInt());
		} catch (Exception e) {
			System.out.println("Erro ao ler a produção.");
		}
		
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
		
		

		ler.close();
		

	}

}


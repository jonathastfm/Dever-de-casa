import java.util.Scanner;

public class Gratificacao {
	
	private int producao[][] = new int[3][2];
	private double aliquota[] = new double[3];
	
	Scanner ler = new Scanner(System.in);
	
	public int[][] getProducao() {
		return producao;
	}
	public void setProducao() {
		
		for(int i = 0; i < 3; i++) {
						
				System.out.println("Informe o valor minimo da " + (i+1) + " faixa" );
				producao[i][0] = ler.nextInt();
				System.out.println("Informe o valor maximo da " + (i+1) + " faixa" );
				producao[i][1] = ler.nextInt();
		}
		
		
	}
	public double[] getAliquota() {
		return aliquota;
	}
	public void setAliquota() {
		for(int i = 0; i < 3; i++) {
			
			System.out.println("Infome o valor da aliquota: ");
			aliquota[i] = ler.nextDouble();
	}
	}

	public double valorGratificao(int qtdItens)
	{
		if(qtdItens <= producao[0][1]) return aliquota[0];
		else if(qtdItens <= producao[1][1]) return aliquota[1];
		else return aliquota[2];
	}
}

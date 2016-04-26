package app;

import algoritmos.C4dot5Tree;
import algoritmos.Knn;
import algoritmos.Mlp;
import data.DataSetting;

public class Main {
	
	public static void main(String[] args) throws Exception {
		

		
		DataSetting dados = new DataSetting();
		DataSetting dados2 = new DataSetting();
		DataSetting dados3 = new DataSetting();
		
		 
		
		Knn knn = new Knn(5, dados);
		knn.classifierBuild();
		
		
		C4dot5Tree arvore = new C4dot5Tree(dados2);
		arvore.classifierBuild();
		arvore.setPoda(true);
		arvore.setValorPoda(1.0f);
		
		Mlp mlp = new Mlp(dados3);
		mlp.classifierBuild();
		mlp.getMlp().setAutoBuild(true);
		mlp.getMlp().setLearningRate(.3);
		mlp.getMlp().setHiddenLayers("40");
		mlp.setNumEpoc(500);
		
		Thread t1 = new Thread() {
			public void run() {
				System.out.println(knn);
			}
		};
		
		Thread t2 = new Thread() {
			public void run() {
				System.out.println(arvore);
			}
		};
		
		Thread t3 = new Thread() {
			public void run() {
				System.out.println(mlp);
			}
		};
		
		t1.start();
		t2.start();
		t3.start();

		//arvore.mostrarArvore();

	}

}

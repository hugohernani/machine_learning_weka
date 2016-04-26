package algoritmos;

import java.util.Random;

import data.DataSetting;
import excecoes.DataExec;
import excecoes.EvaluationExec;
import excecoes.TreeBuidExec;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;

public class C4dot5Tree implements Algoritmos {
	
	private DataSetting data;
	private J48 arvore;
	private Evaluation eval;
	
	public C4dot5Tree(DataSetting data) throws DataExec{
		try {
		this.data = data;
		} catch (Exception e) {
			throw new DataExec("Erro na instanciação do DataSetting");
		}
		this.arvore = new J48();
	}
	
	public void classifierBuild() throws TreeBuidExec {
		try {
			this.arvore.buildClassifier(this.data.getBaseTreinamento());
		} catch (Exception e) {
			throw new TreeBuidExec("Erro na hora do builder do classificador da arvore");
		}
	}
	
	public void setPoda(boolean poda) {
		this.arvore.setUnpruned(poda);
	}
	
	public void setValorPoda(float num) {
		this.arvore.setConfidenceFactor(num);
	}
	
	public Evaluation buildEval() throws EvaluationExec {
		try {
			this.eval = new Evaluation(this.data.getData());
		} catch (Exception e) {
			throw new EvaluationExec("Erro na craicao da instancia da Evaluation");
		}
		try {
			this.eval.crossValidateModel(this.arvore, this.data.getData(), 10, new Random(0));
		} catch (Exception e) {
			throw new EvaluationExec("Erro na chamada de metodo evaluateModel");
		}
		return eval;
	}
	
	public void mostrarArvore() throws Exception {
		System.out.println(this.arvore.toString());
	}
	
	@Override
	public String toString() {
		try {
			buildEval();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Algoritmo C4.5: \n" + eval.toSummaryString();
	}
	
	

}

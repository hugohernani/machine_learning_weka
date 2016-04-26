package algoritmos;

import java.util.Random;

import data.DataSetting;
import excecoes.DataExec;
import excecoes.EvaluationExec;
import excecoes.IbkBuildExec;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;

public class Knn implements Algoritmos{
	
	private DataSetting data;
	private Classifier ibk;
	private Evaluation eval;
	
	public Knn(int numViz, DataSetting data) throws DataExec {
		try {
		this.data = data;
		} catch (Exception e) {
			throw new DataExec("Erro na instanciação do DataSetting");
		}
		this.ibk = new IBk(numViz);
	}
	
	public void classifierBuild() throws IbkBuildExec{
		try {
			this.ibk.buildClassifier(this.data.getBaseTreinamento());
		} catch (Exception e) {
			throw new IbkBuildExec("Erro na hora do builder do classificador do Knn");
		}
	}
	
	public Evaluation buildEval() throws EvaluationExec {
		try {
			this.eval = new Evaluation(this.data.getData());
		} catch (Exception e) {
			throw new EvaluationExec("Erro na criacao da instancia da Evaluation");
		}
		try {
			this.eval.crossValidateModel(this.ibk, this.data.getData(), 10, new Random(0));
		} catch (Exception e) {
			throw new EvaluationExec("Erro na chamada de metodo evaluateModel");
		}
		return eval;
	}
	
	@Override
	public String toString() {
		try {
			buildEval();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Algoritmo KNN: \n" + eval.toSummaryString();
	}

}

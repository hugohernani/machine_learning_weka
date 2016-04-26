package algoritmos;

import java.util.Random;

import data.DataSetting;
import excecoes.DataExec;
import excecoes.EvaluationExec;
import excecoes.MlpExec;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;

public class Mlp implements Algoritmos{
	
	private DataSetting data;
	private MultilayerPerceptron mlp;
	private Evaluation eval;
	
	public Mlp(DataSetting data) throws DataExec {
		try {
		this.data = data;
		} catch (Exception e) {
			throw new DataExec("Erro na instanciação do DataSetting");
		}
		this.mlp = new MultilayerPerceptron();
	}
	
	public void classifierBuild() throws MlpExec{
		try{
			this.mlp.buildClassifier(this.data.getBaseTreinamento());
		}catch (Exception e) {
			throw new MlpExec("Erro na hora do builder do classificador da MLP");
		}
	}
	
	public void setNumEpoc(int numEpoc) {
		this.mlp.setTrainingTime(numEpoc);
	}
	
	public MultilayerPerceptron getMlp() {
		return this.mlp;
	}
	
	public Evaluation buildEval() throws EvaluationExec {
		try {
			this.eval = new Evaluation(this.data.getData());
		} catch (Exception e) {
			throw new EvaluationExec("Erro na criacao da instancia da Evaluation");
		}
		try {
			this.eval.crossValidateModel(this.mlp, this.data.getData(), 10, new Random(0));
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
		try {
			return "Algoritmo MLP:\n" + eval.toSummaryString() + "\n" + eval.toMatrixString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

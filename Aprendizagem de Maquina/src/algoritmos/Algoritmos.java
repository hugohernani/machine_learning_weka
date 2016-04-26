package algoritmos;

import excecoes.EvaluationExec;
import excecoes.IbkBuildExec;
import excecoes.MlpExec;
import excecoes.TreeBuidExec;
import weka.classifiers.Evaluation;

public interface Algoritmos {
	
	abstract void classifierBuild() throws IbkBuildExec, TreeBuidExec, MlpExec;
	abstract Evaluation buildEval() throws EvaluationExec;

}

package clustering;

import data.Clustering;
import data.DataClustering;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SelfOrganizingMap;

public class RedeSOM implements Clustering{
	private DataClustering data;
	private SelfOrganizingMap som;
	private ClusterEvaluation eval;
	
	private int numEpoc, ordEpoc, width, height;
	private double learnRate;
	
	
	
	public RedeSOM(DataClustering data) {
		this.som = new SelfOrganizingMap();
		this.eval = new ClusterEvaluation();
		this.data = data;
	}
	
	public void setValues(int numEpoc, int ordEpoc, int width, int height, double learnRate) {
		this.numEpoc = numEpoc;
		this.ordEpoc = ordEpoc;
		this.width = width;
		this.height = height;
		this.learnRate = learnRate;
	}

	@Override
	public void buildCluster() {
		try {
			som.setConvergenceEpochs(this.numEpoc);
			som.setLearningRate(this.learnRate);
			som.setOrderingEpochs(ordEpoc);
			som.setWidth(this.width);
			som.setHeight(this.height);
			som.setCalcStats(true);
			som.buildClusterer(data.getInstances());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void evalCluster() {
		try {
			eval.setClusterer(this.som);
			eval.evaluateClusterer(this.data.getInstances());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "\nResultado da Rede SOM: \n" 
				+ this.eval.clusterResultsToString();
	}
}

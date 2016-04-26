package clustering;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import data.Cluster;
import data.Clustering;
import data.DataClustering;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Clusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.DistanceFunction;
import weka.core.Instance;
import weka.core.Instances;

public class Kmeans implements Clustering{
	
	private DataClustering data;
	private int numClust;
	private DistanceFunction distanceFunction;
	private SimpleKMeans simpleKmeans;
	private ClusterEvaluation eval;
	
	public Kmeans(DataClustering data, int numClust, DistanceFunction distanceFunction) {
		this.data = data;
		this.numClust = numClust;
		this.distanceFunction = distanceFunction;
		this.simpleKmeans = new SimpleKMeans();
		this.eval = new ClusterEvaluation();
	}
	

	@Override
	public void buildCluster() {
		try {
			simpleKmeans.setSeed(10);
			simpleKmeans.setPreserveInstancesOrder(true);
			simpleKmeans.setDistanceFunction(this.distanceFunction);
			simpleKmeans.setNumClusters(this.numClust);
			simpleKmeans.buildClusterer(this.data.getInstances());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void evalCluster() {
		try {
			eval.setClusterer(this.simpleKmeans);
			eval.evaluateClusterer(this.data.getInstances());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "\nResultado do Agrupamento por Kmeans: \n" 
				+ this.eval.clusterResultsToString();
	}

}

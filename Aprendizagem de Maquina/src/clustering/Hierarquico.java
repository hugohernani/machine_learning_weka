package clustering;

import data.Clustering;
import data.DataClustering;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.HierarchicalClusterer;
import weka.core.DistanceFunction;
import weka.core.SelectedTag;

public class Hierarquico implements Clustering{
	private DataClustering data;
	private int numClust;
	private DistanceFunction distFunc;
	private HierarchicalClusterer cluster;
	private ClusterEvaluation eval;
	
	public Hierarquico(DataClustering data, int numClust, DistanceFunction distFunc) {
		this.cluster = new HierarchicalClusterer();
		this.eval = new ClusterEvaluation();
		this.data = data;
		this.numClust = numClust;
		this.distFunc = distFunc;
	}
	
	@Override
	public void buildCluster() {
		try {
			SelectedTag s = new SelectedTag(1, HierarchicalClusterer.TAGS_LINK_TYPE);
			cluster.setDistanceFunction(this.distFunc);
			cluster.setNumClusters(this.numClust);
			cluster.setLinkType(s);
			cluster.buildClusterer(this.data.getInstances());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void evalCluster() {
		try {
			eval.setClusterer(this.cluster);
			eval.evaluateClusterer(this.data.getInstances());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "\nResultado do Agrupamento Hierarquico: \n" 
				+ this.eval.clusterResultsToString();
	}
}

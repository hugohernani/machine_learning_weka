package clustering;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import data.Cluster;
import data.Clustering;
import data.DataClustering;
import weka.clusterers.Clusterer;
import weka.core.DistanceFunction;
import weka.core.Instance;
import weka.core.Instances;

public class Kmeans implements Clustering{
	
	private DataClustering data;
	private int numClust;
	private DistanceFunction distanceFunction;
	
	private List<Cluster> clusters;
	
	public Kmeans(DataClustering data, int numClust, DistanceFunction distanceFunction) {
		this.data = data;
		this.numClust = numClust;
		this.distanceFunction = distanceFunction;
		this.clusters = new ArrayList<Cluster>();
	}
	
	public void initialize(){
		
		for (int i = 0; i < numClust; i++) {
			Cluster cluster = new Cluster(i);
			Integer rand_number = new Random().nextInt(this.data.getInstances().numAttributes());
			Instance centroid = this.data.getInstances().instance(rand_number);
			cluster.setCentroid(centroid);
			clusters.add(cluster);
		}
	}

	@Override
	public void buildCluster() {
		initialize();
	}

	@Override
	public void evalCluster() {
		Instances instances = this.data.getInstances();
		double max = Double.MAX_VALUE;
		double min = max;
		double distance = 0.0;
		do{
			for (int i = 0; i < clusters.size(); i++) {
				Cluster currentCluster = clusters.get(i);
				Instance centroid = currentCluster.getCentroid();
				for (int j = 0; j < instances.size(); j++) {
					Instance currentInstance = instances.instance(j);
					distance = distanceFunction.distance(centroid, currentInstance);
					if(distance < min){
						min = distance;
						currentCluster.addInstance(currentInstance);
					}
				}
			}
			update_centroids(instances);
		}while(distance != 0);
		
	}
	
	public void update_centroids(Instances instances){
		// TODO
	}

}

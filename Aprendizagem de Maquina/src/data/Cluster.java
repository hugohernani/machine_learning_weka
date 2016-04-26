package data;

import java.util.ArrayList;
import java.util.List;

import weka.core.Instance;
import weka.core.Instances;

public class Cluster {

	public List<Instance> instances;
	public Instance centroid;
	public int id;
	
	//Creates a new Cluster
	public Cluster(int id) {
		this.id = id;
		this.instances = new ArrayList<Instance>()
		this.centroid = null;
	}
 
	public List<Instance> getInstances() {
		return instances;
	}
	
	public void addInstance(Instance instance) {
		instances.add(instance);
	}
 
	public void setInstances(List<Instance> instances) {
		this.instances = instances;
	}
 
	public Instance getCentroid() {
		return centroid;
	}
 
	public void setCentroid(Instance centroid) {
		this.centroid = centroid;
	}
 
	public int getId() {
		return id;
	}
}
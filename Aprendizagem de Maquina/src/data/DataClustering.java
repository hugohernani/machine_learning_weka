package data;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class DataClustering {
	
	private static final String DERMA = "./database/dermatology_cluster.arff";
	private DataSource data;
	private Instances instances;
	
	public DataClustering() {
		try {
			this.data = new DataSource(DERMA);
			this.instances = data.getDataSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DataSource getData() {
		return data;
	}

	public Instances getInstances() {
		return instances;
	}
	
	
}

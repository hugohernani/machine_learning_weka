package app;

import clustering.Hierarquico;
import clustering.Kmeans;
import clustering.RedeSOM;
import data.DataClustering;
import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;

public class Main {
	
	public static void main(String[] args) throws Exception{
		
		Thread t1 = new Thread() {
			public void run() {
				DataClustering data1 = new DataClustering();
				
				DistanceFunction euclides = new EuclideanDistance();
				
				Hierarquico hc = new Hierarquico(data1, 6, euclides);
				
				hc.buildCluster();
				hc.evalCluster();
				
				System.out.println(hc);
			}
		};
		
		Thread t2 = new Thread() {
			public void run() {
				DataClustering data2 = new DataClustering();
				
				RedeSOM rs = new RedeSOM(data2);
				rs.setValues(500, 50, 4, 4, .3);
				
				rs.buildCluster();
				rs.evalCluster();

				System.out.println(rs);
			}
		};
		
		Thread t3 = new Thread(){
			public void run(){
				DataClustering data3 = new DataClustering();
				
				DistanceFunction euclides = new EuclideanDistance();
				
				Kmeans kmeans = new Kmeans(data3, 3, euclides);
				
				kmeans.buildCluster();
				kmeans.evalCluster();
				
				System.out.println(kmeans);
			}
		};
		
		t1.start();
		t2.start();
		t3.start();
	}
}

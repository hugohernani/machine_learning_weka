package app;

import clustering.Hierarquico;
import clustering.RedeSOM;
import data.DataClustering;
import weka.core.DistanceFunction;
import weka.core.EuclideanDistance;
import weka.core.ManhattanDistance;

public class Main2 {
	
	public static void main(String[] args) throws Exception{
		
		DataClustering data1 = new DataClustering();
		DataClustering data2 = new DataClustering();
		
		DistanceFunction euclides = new EuclideanDistance();
		DistanceFunction manhattan = new ManhattanDistance();
		
		Hierarquico hc = new Hierarquico(data1, 6, manhattan);
		
		RedeSOM rs = new RedeSOM(data2);
		rs.setValues(500, 50, 4, 4, .3);
		
		rs.buildCluster();
		rs.evalCluster();
		
		//hc.buildCluster();
		//hc.evalCluster();
		
		//System.out.println(hc);
		System.out.println(rs);
		
	}
}

package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import weka.core.Instances;

public class DataSetting {
	
	private Instances data;
	private Instances treinameto;
	private Instances teste;
	private BufferedReader baseCompleta;
	
	private static final String DERMA = "./database/dermatology.arff";
	
	public static BufferedReader dataBaseFile(String direct) {
		BufferedReader data = null;
		try {
			data = new BufferedReader(new FileReader(direct));
		} catch(FileNotFoundException e) {
			System.err.println("File not found: " + direct);
		}
		return data;
	}
	
	public DataSetting() throws IOException {
		this.baseCompleta = dataBaseFile(DERMA);
		this.data = new Instances(baseCompleta).resample(new Random(1));
		this.data.setClassIndex(data.numAttributes() - 1);
		baseCompleta.close();
		this.treinameto = data.trainCV(4, 2);
		this.teste = data.testCV(4, 2);
	}
	
	public Instances getBaseTreinamento() {
		return this.treinameto;
	}
	
	public Instances getBaseTeste() {
		return this.teste;
	}
	
	public Instances getData() {
		return this.data;
	}


}

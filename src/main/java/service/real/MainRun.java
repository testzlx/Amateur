package service.real;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MainRun {
	@SuppressWarnings("static-access")
	public static void main(String args[]){
		/*
		String traindata="/Users/zhanglinxing/github/Random-Forest/Data.txt";
		String testdata="/Users/zhanglinxing/github/Random-Forest/Test.txt";
		int numTrees=100;
		
		DescribeTrees DT = new DescribeTrees(traindata);
		ArrayList<int[]> Input=DT.CreateInput(traindata);
		int categ=0;
		
		DescribeTrees DTT = new DescribeTrees(traindata);
		ArrayList<int[]> Test=DTT.CreateInput(testdata);
		
		for(int k=0;k<Input.size();k++){if(Input.get(k)[Input.get(k).length-1]<categ)continue;else{categ=Input.get(k)[Input.get(k).length-1];}}
		
		RandomForest RaF =new RandomForest(numTrees, Input, Test);
		RaF.C=categ;
		RaF.M=Input.get(0).length-1;
		RaF.Ms=(int)Math.round(Math.log(RaF.M)/Math.log(2)+1);
		RaF.Start();
		*/
		//System.out.println(ObjectToByte(new Student(2,"34")));
		//
		//System.out.println(ObjectToByte(new String("34")));
		for(byte b:ObjectToByte(new Student(23,"3f"))){
			System.out.println(b);
		}
		System.out.println("--------------------");

		for(byte b:ObjectToByte(new Student(23,"3f"))){
			System.out.println(b);
		}


	}

	private static class Student implements Serializable{
		int age;
		String name;
		public Student(int age,String name){
			this.age = age;
			this.name = name;
		}
	}

	public static byte[] ObjectToByte(java.lang.Object obj) {
		byte[] bytes = null;
		try {
			// object to bytearray
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);

			bytes = bo.toByteArray();

			bo.close();
			oo.close();
		} catch (Exception e) {
			System.out.println("translation" + e.getMessage());
			e.printStackTrace();
		}
		return bytes;
	}
}

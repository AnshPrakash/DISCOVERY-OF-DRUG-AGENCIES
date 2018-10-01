import java.util.*;
import java.io.*;

public class SAT {

	

	static BufferedWriter bw =null;
	static int[][] variable_names=null;

	static variable_name_count=0;
	public static void Non_Empty(){
		try{
			for (int k=1;k<variable_names[0].length;k++) {
				for (int i=1;i<variable_names.length;i++ ) {
					bw.write(variable_names[i][k]+" ");
				}
				bw.write("0\n");
			}
		}catch(Exception e){
			System.out.println("Something went wrong in Non_Empty function");
		}
	}

	public static void AtLeast_OneNode(boolean[][] adjacencyMatrix,int k_subgraph){
		try{
			for (int i=1;i<variable_names.length ;i++ ) {
				for (int k=1;k<variable_names[0].length;k++) {
					bw.write(variable_names[i][k]+" ");
				}
				bw.write("0\n");
			}
		}catch(Exception e){
			System.out.println("Something went wrong in AtLeast_OneNode function");
		}
	}
	public static void AllKgraphComplete(boolean[][] adjacencyMatrix,int k_subgraph){
		try{
			for (int k=1;k<variable_names[0].length;k++) {
				for (int i=1;i<variable_names.length;i++) {
					for (int j=i+1;j<variable_names.length ;j++ ) {
						if(adjacencyMatrix[i][j]==false){
							bw.write("-"+variable_names[i][k]+" -"+variable_names[j][k]+" 0\n");
						}
					}
				}
			}
		}catch(Exception e){
			System.out.println("Something went wrong in AtLeast_OneNode function");
		}
	}

	// public static void No_Subgraph(boolean[][] adjacencyMatrix,int k){
	// 	int temp=variable_name_count+1;
	// 	for (int i=1;i<variable_names.length;i++ ) {
	// 		for (int j=i+1;j<variable_names.length;j++) {
	// 			for (int k=1;k<variable_names[0].length;k++) {
	// 				if (adjacencyMatrix[i][j]==true) {
	// 					bw.write(temp+" ");
	// 					temp+=1;
	// 				}
	// 			}
	// 			bw.write("0\n");
	// 		}
	// 	}
	// }
	// public static void AtLeastOneEdge(boolean[][] adjacencyMatrix,int k){

	// }

	public static void GenerateSAT(boolean[][] adjacencyMatrix,int k){

		variable_names=new int[adjacencyMatrix.length][k+1];
		for (int j=0;j<variable_names.length ;j++ ) {
			variable_names[j][0]=0;
		}
		for (int j=0;j<variable_names[0].length ;j++ ) {
			variable_names[0][j]=0;
		}
		for (int i=1;i<variable_names.length;i++ ) {
			for (int j=1;j<variable_names[0].length ;j++ ) {
				variable_names[i][j]=k*(i-1)+j;
			}
		}
		variable_name_count=(variable_names.length-1)*(variable_names[0].length-1);
		File sat_input_file=new File("./test.satinput");
		try{
			bw = new BufferedWriter(new FileWriter(sat_input_file));
			bw.write("p cnf ");
			int num_v = adjacencyMatrix.length-1;
			int numV = (num_v*num_v)*(2*k+1)+(num_v*k);
			bw.write(Integer.toString(numV));
			bw.write(" ");
			int numC = 2*(num_v*num_v)*(4*k+1) + (num_v);
			bw.write(Integer.toString(numC)); 
			bw.write("\n");
		}
		catch(Exception e){
			System.out.println("Something went wrong! reading a file");
		}

		Non_Empty();
		// No_Subgraph(boolean[][] adjacencyMatrix,int k);
		AtLeast_OneNode(adjacencyMatrix,k);
		// AtLeastOneEdge(adjacencyMatrix,k);
		AllKgraphComplete(adjacencyMatrix,k);
		try{
			bw.close();
		}
		catch(Exception e){
			System.out.println("bw.close() didn't work haha");
		}
	}



	public static void main(String[] args) {
		File file = new File("./"+args[0]+ ".graph");
		try{
			Scanner scn = new Scanner(file);
			int n = scn.nextInt();
			int e = scn.nextInt();
			int k = scn.nextInt();

			boolean[][] adjacencyMatrix = new boolean[n+1][n+1];
			for (int i=0;i<adjacencyMatrix.length ;i++ ) {
				for (int j=0;j<adjacencyMatrix[0].length ;j++ ) {
					adjacencyMatrix[i][j]=false;
				}
			}

			int idx = 0;
			int i = 0;
			int j = 0;
			for(idx = 0;idx<e;idx++){
				i = scn.nextInt();
				j = scn.nextInt();
				adjacencyMatrix[i][j] =true;
				adjacencyMatrix[j][i] =true;
			}
			GenerateSAT(adjacencyMatrix,k);
		}catch(FileNotFoundException ex){
			// exception
		}	

	}
}

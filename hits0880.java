// ROHAN MATHEW PANICKER CS610 0880 prp
// author: ROHAN MATHEW PANICKER
// NJIT ID: 31460880
// DATA STRUCTURES AND ALGORITHMS
// HITS ALGORITHM

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class hits0880 {

	public hits0880() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String args[]) {
		
		try {
			int iterationValue = Integer.parseInt(args[0]);//iteration Value
			int initialValueIdentifier = Integer.parseInt(args[1]);//initial Value
			String inputFileLocation = args[2];
			int totalNodes = 0;
			int totalEdges = 0;
			boolean check=true;
			int adjacencyMatrix[][] = null;
			
			if(initialValueIdentifier==0 || initialValueIdentifier==1 || initialValueIdentifier==-1 || initialValueIdentifier==-2) {
				
				File fileCheck = new File(inputFileLocation);
				
				if(fileCheck.exists()){
					
					Scanner sc = new Scanner(new BufferedReader(new FileReader(inputFileLocation)));
					
					while(sc.hasNextLine()) {
						
						String[] line = sc.nextLine().trim().split(" ");
						
						if(check) {
							totalNodes = Integer.parseInt(line[0]);
							totalEdges = Integer.parseInt(line[1]);
							adjacencyMatrix = new int[totalNodes][totalNodes];
							check=false;
						}
						else if(!check) {
							
							for (int i=0; i<adjacencyMatrix.length; i++) {
					        	 
					            for (int j=0; j<line.length; j++) {
					               adjacencyMatrix[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = 1;
					            }
					         }
							
						}
						
				         
				    }					
					
				}else{
					System.out.println("file not exist.");
				}
				
				
			}
			else {
				
				System.out.println("Enter the initial Values as(0/1/-1/-2)");
				
			}
			
			//System.out.println("Iterations : " + iterationValue + " Initial Value : " + initialValueIdentifier + " Nodes : " + totalNodes + " Edges : " + totalEdges);
			//Very Large Graph
			if( totalNodes > 10  ) {
				
				iterationValue = 0;
				initialValueIdentifier = -1;
				
			}
			
			
		    //PageRank pr = new PageRank();
			if(iterationValue == 0 || iterationValue < 0) {
				hits0880 hk = new hits0880();
				hk.calculateHITS(iterationValue, initialValueIdentifier, totalNodes, adjacencyMatrix);
			}
			else {
				hits0880 hk = new hits0880();
			    hk.computeHITS(iterationValue,initialValueIdentifier,totalNodes, adjacencyMatrix);
			}
		    
		    
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
	}
	
	
	public static void computeHITS(int iteration,int initialValue,int nodes,int aMatrix[][]) {
		
		double outHubScore[] = new double[nodes];
		double inAuthScore[] = new double[nodes];
		double outNewHubScore[] = new double[nodes];
		double inNewAuthScore[] = new double[nodes];
		
		
		for(int i=0;i<nodes;i++) {
			
			if(initialValue==0) {
				outHubScore[i] = 0.0;
				inAuthScore[i] = 0.0;
			}
			else if(initialValue==1) {
				outHubScore[i] = 1.0;
				inAuthScore[i] = 1.0;			
			}
			else if(initialValue==-1) {
				outHubScore[i] = 1.0/nodes;
				inAuthScore[i] = 1.0/nodes;
			}
			else if(initialValue==-2) {
				outHubScore[i] = 1.0/(Math.sqrt(nodes));
				inAuthScore[i] = 1.0/(Math.sqrt(nodes));
			}
		}
		
		
		System.out.print("Base : 0 :");
		for(int i = 0 ; i < nodes ; i++ ) {
			System.out.printf(" A/H[%d] = %f/%f",i,inAuthScore[i],outHubScore[i]);
		}
		
		
		System.out.println();
		
		
		
		for(int r=0;r<iteration;r++) {
			//to calculate auth score and hubscore
			for(int j=0; j< nodes; j++) {	
			 	
				boolean enter = true;
				
				for(int i=0;i<nodes;i++) {
					
					if(aMatrix[i][j]==1 && enter) {
						inNewAuthScore[j] = outHubScore[i];
						enter = false;
					}
					else if(aMatrix[i][j]==1 && !enter) {
						inNewAuthScore[j] = inNewAuthScore[j] + outHubScore[i];
					}	
				}
				
				inAuthScore[j] = inNewAuthScore[j];
		
			}
			
			for(int i = 0 ; i < nodes ; i++) {
				
				boolean enter=true;
				
				for(int j=0;j<nodes;j++) {
					
					if(aMatrix[i][j]==1 && enter) {
						outNewHubScore[i] = inNewAuthScore[j];
						enter = false;
					}
					else if(aMatrix[i][j]==1 && !enter) {
						outNewHubScore[i] = outNewHubScore[i] + inNewAuthScore[j]; 
					}	
				}
				
				outHubScore[i] = outNewHubScore[i];
			}
			
			//Normalisation
			 
			double sumAuthScore = 0.0;
			double sumHubScore = 0.0;
			
			for(int i=0 ; i<inAuthScore.length ; i++) {
				sumAuthScore = sumAuthScore + inAuthScore[i];
				sumHubScore = sumHubScore + outHubScore[i];
			}
			
			for(int i=0 ; i<inAuthScore.length ; i++) {
				inAuthScore[i]=inAuthScore[i]/Math.sqrt(sumAuthScore);
				outHubScore[i]=outHubScore[i]/Math.sqrt(sumHubScore);
			}
			
			//Normalisation ends
			
			System.out.printf("Iter : %d :",(r+1));
			for(int k = 0 ; k < nodes ; k++ ) {
				System.out.printf(" A/H[%d] = %f/%f",k,inAuthScore[k],outHubScore[k]);
			}
			System.out.println();
			
		}//for ends
		
		
		
		
	}
	
	public static void calculateHITS(int iteration,int initialValue,int nodes,int aMatrix[][]) {
		
		double outHubScore[] = new double[nodes];
		double inAuthScore[] = new double[nodes];
		double outNewHubScore[] = new double[nodes];
		double inNewAuthScore[] = new double[nodes];
		double legacyHubScore[] = new double[nodes];
		double legacyAuthScore[] = new double[nodes];
		double errorRate = 0.0;
		
		for(int i=0;i<nodes;i++) {
			
			if(initialValue==0) {
				outHubScore[i] = 0.0;
				inAuthScore[i] = 0.0;
			}
			else if(initialValue==1) {
				outHubScore[i] = 1.0;
				inAuthScore[i] = 1.0;			
			}
			else if(initialValue==-1) {
				outHubScore[i] = 1.0/nodes;
				inAuthScore[i] = 1.0/nodes;
			}
			else if(initialValue==-2) {
				outHubScore[i] = 1.0/(Math.sqrt(nodes));
				inAuthScore[i] = 1.0/(Math.sqrt(nodes));
			}
		}
		
		if(nodes<=10) {
			System.out.print("Base : 0 :");
			for(int i = 0 ; i < nodes ; i++ ) {
				System.out.printf(" A/H[%d] = %f/%f",i,inAuthScore[i],outHubScore[i]);
				System.out.println();
			}
		}
		
		if(iteration == 0 || iteration == -5) {
			errorRate = 1.0/100000.0;
		}
		else if(iteration == -1) {
			errorRate = 1.0/10.0;
		}
		else if(iteration == -2) {
			errorRate = 1.0/100.0;
		}
		else if(iteration == -3) {
			errorRate = 1.0/1000.0;
		}
		else if(iteration == -4) {
			errorRate = 1.0/10000.0;
		}
		else if(iteration == -6) {
			errorRate = 1.0/1000000.0;
		}
		else {
			double num = iteration;
			errorRate = 1.0/num;
		}
		
		//System.out.println("The error rate : " + errorRate);
		
		boolean continueLoop = true;
		int r=0;
		
		while(continueLoop) {
			
			for(int i=0; i<inAuthScore.length ; i++ ) {
				legacyAuthScore[i] = inAuthScore[i];
				legacyHubScore[i] = outHubScore[i];
			}
			
			for(int j=0; j< nodes; j++) {	
			 	
				boolean enter = true;
				
				for(int i=0;i<nodes;i++) {
					
					if(aMatrix[i][j]==1 && enter) {
						inNewAuthScore[j] = outHubScore[i];
						enter = false;
					}
					else if(aMatrix[i][j]==1 && !enter) {
						inNewAuthScore[j] = inNewAuthScore[j] + outHubScore[i];
					}	
				}
				
				inAuthScore[j] = inNewAuthScore[j];
		
			}
			
			for(int i = 0 ; i < nodes ; i++) {
				
				boolean enter=true;
				
				for(int j=0;j<nodes;j++) {
					
					if(aMatrix[i][j]==1 && enter) {
						outNewHubScore[i] = inNewAuthScore[j];
						enter = false;
					}
					else if(aMatrix[i][j]==1 && !enter) {
						outNewHubScore[i] = outNewHubScore[i] + inNewAuthScore[j]; 
					}	
				}
				
				outHubScore[i] = outNewHubScore[i];
			}
			
			//Normalisation
			 
			double sumAuthScore = 0.0;
			double sumHubScore = 0.0;
			
			for(int i=0 ; i<inAuthScore.length ; i++) {
				sumAuthScore = sumAuthScore + inAuthScore[i];
				sumHubScore = sumHubScore + outHubScore[i];
			}
			
			for(int i=0 ; i<inAuthScore.length ; i++) {
				inAuthScore[i]=inAuthScore[i]/sumAuthScore;
				outHubScore[i]=outHubScore[i]/sumHubScore;
			}
			
			//Normalisation ends
			if(nodes<=10) {
				System.out.printf("Iter : %d :",(++r));
				for(int k = 0 ; k < nodes ; k++ ) {
					System.out.printf(" A/H[%d] = %f/%f",k,inAuthScore[k],outHubScore[k]);
				}
				System.out.println();
			}
		
			//Error rate computation
			for(int i=0; i<nodes; i++) {
				
				if(Math.abs(inAuthScore[i] - legacyAuthScore[i])<errorRate && Math.abs(outHubScore[i] - legacyHubScore[i])<errorRate) {
					continueLoop = false;
				}
				else {
					continueLoop = true;
					break;
				}
				
			}
			//Error rate computation ends

			
		}//while
		
		// if nodes are greater than 10 
		if(nodes>10) {
			System.out.printf("Iter : %d :",r);
			for(int k = 0 ; k < nodes ; k++ ) {
				System.out.printf(" A/H[%d] = %f/%f",k,inAuthScore[k],outHubScore[k]);
				System.out.println();
			}
			
		}
		
	}

}
 
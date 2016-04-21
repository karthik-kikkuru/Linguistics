import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;


public class Linguistics {

	public static void main(String args[]) throws FileNotFoundException{
		
		Scanner sc = new Scanner(new File("input1.txt"));
		
		LinkedList<node> Labels = new LinkedList<node>();
		LinkedList<node2> assoc = new LinkedList<node2>();
		
		String S = sc.nextLine();
		while(sc.hasNextLine()){
			String K = S.substring(0,1);
			S = S.replaceAll("\\s","");	
			
			for(int i=3;i<S.length();i=i+2){
				node obj = new node();
				obj.num = Integer.parseInt(S.substring(i,i+1));
				obj.Label = K;
				Labels.add(obj);
			}
			S =sc.nextLine();
		}
		for(int i = 0;i<Labels.size();i++){
			//System.out.println(Labels.get(i).num+Labels.get(i).Label);
		}
			
		S = S.replaceAll("\\s","");
		S = S.replace("(","");
		S = S.replace(")","");
		S = S.replace(",","");
		S = S.replace("}","");
		for(int i=3;i<S.length()-1;i=i+2){
			node2 obj2 = new node2();
			obj2.num1 = Integer.parseInt(S.substring(i,i+1));
			obj2.num2 = Integer.parseInt(S.substring(i+1,i+2));
			assoc.add(obj2);
		}
		
		
		
		String output = "";
		for(int i= 0;i<Labels.size();i++){
			//Graph graph = new MultiGraph("");
			for(int j = 0;j<assoc.size();j++){
				for(int k = 0;k<Labels.size();k++){
					if(Labels.get(i).num==assoc.get(j).num1 && Labels.get(k).num == assoc.get(j).num2){
						//System.out.print(Labels.get(i).Label+"\n"+"|"+"\n"+Labels.get(k).Label+"\r");
						//System.out.print("\r")
						output = output + Labels.get(i).Label+"\n"+"|"+"\n"+Labels.get(k).Label+"\n";
						
					}
				}
			}
		}System.out.print(output);
	}
}

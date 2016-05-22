import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Linguistics {

	LinkedList<node5> Assoc = new LinkedList<node5>();
	LinkedList<node6> FinalAssoc = new LinkedList<node6>();
	LinkedList<node1> Labels = new LinkedList<node1>();
	PrintStream out = null;
	String output = null;
	int count;
	int position;
	public static void main(String args[]) throws FileNotFoundException{

		Scanner sc = new Scanner(new File("input1.txt"));
		Linguistics class_obj = new Linguistics();
		
		//LinkedList<node2> Prec = new LinkedList<node2>();
		//LinkedList<node3> FinalPrec = new LinkedList<node3>();
		//LinkedList<node4> Finallist = new LinkedList<node4>();

		//Scan and insert numbers into linked list with their corresponding labels

		while(sc.hasNextLine()){
			String S = sc.nextLine();

			if(S.startsWith("P")){
				//Precedence(S, Labels);
				break;
			}
			if(S.startsWith("A")){
				class_obj.Association(S, class_obj.Labels);
				continue;
			}

			String K = S.substring(0,1);
			S = S.replaceAll("\\s","");	
			int i =3;
			while(i<S.length()){
				node1 obj = new node1();
				if(!S.substring(i+1,i+2).equals(",") && !S.substring(i+1,i+2).equals("}") ){
					String conc = S.substring(i, i+1)+S.substring(i+1,i+2);
					obj.num = Integer.parseInt(conc);
					obj.Label = K;
					class_obj.Labels.add(obj);
					i=i+3;
				}
				else{
					obj.num = Integer.parseInt(S.substring(i,i+1));
					obj.Label = K;
					class_obj.Labels.add(obj);
					i = i+2;
				}
			}
		}		
	}

	private void Association(String S, LinkedList<node1> Labels) throws FileNotFoundException {


		
	   out = new PrintStream(new FileOutputStream("output.dot"));

		//System.out.println("There is nothing for now!!");
		S = S.replaceAll("\\s","");
		S = S.replace("(","");
		S = S.replace(")","");
		//S = S.replace("}","");


		int l =3;
		while(l<S.length()){
			node5 obj5 = new node5();
			if(!S.substring(l+1,l+2).equals(",") && !S.substring(l+1,l+2).equals("}") ){
				String conc = S.substring(l, l+1)+S.substring(l+1,l+2);
				
				obj5.num1 = Integer.parseInt(conc);
				Assoc.add(obj5);
				l=l+3;
			}
			else{
				obj5.num1 = Integer.parseInt(S.substring(l,l+1));
				Assoc.add(obj5);
				l = l+2;
			}
		}


		for (int i = 0;i<Assoc.size();i=i+2){
			node6 obj6 = new node6();
			obj6.num1 = Assoc.get(i).num1;
			obj6.num2 = Assoc.get(i+1).num1;
			FinalAssoc.add(obj6);
		}
		for(int i=0;i<Assoc.size();i++){
			//System.out.println(Assoc.get(i).num1+"   "+Assoc.get(i).num2);
		}

		//Edge(FinalAssoc);

		//**************************************************************************************//	

		
		
		out.print("digraph G{");
		out.println();

		count=1;
		position = 0;
		for(int i = 0;i<FinalAssoc.size()-1;i++)
		{
			if(FinalAssoc.get(i).num1 == FinalAssoc.get(i+1).num1)
			{
				for(int j = 0;j<Labels.size();j++)
				{
					if(FinalAssoc.get(i).num2  == Labels.get(j).num)
					{
						out.println(count+" " + "[label="+ Labels.get(j).Label +"];");
						count++;
					}
				}
				position++;
			}
			
			else
			{
				if(position > 0)
				{
					for(int j = 0;j<Labels.size();j++)
					{
						if(FinalAssoc.get(i).num2  == Labels.get(j).num){
							out.println(count+" " + "[label="+ Labels.get(j).Label +"];");
							count++;
						}
						
					}
					for(int j = 0;j<Labels.size();j++)
					{
						if(FinalAssoc.get(i).num1  == Labels.get(j).num){
							out.println(count+" " + "[label="+ Labels.get(j).Label +"];");
							count++;
						}
						
					}
					int temp1 = count -1;
					for(int k = 0;k<=position;k++)
					{
						int temp2 = count-2;
						out.println(temp1+"->"+temp2);
						count = count - 1;
					}
					count=count+position+1;
					position=0;
				}
				else
				{
					for(int j = 0;j<Labels.size();j++)
					{
						if(FinalAssoc.get(i).num1  == Labels.get(j).num){
							out.println(count+" " + "[label="+ Labels.get(j).Label +"];");
							count++;
						}
	
						if(FinalAssoc.get(i).num2  == Labels.get(j).num){
							out.println(count+" " + "[label="+ Labels.get(j).Label +"];");
							count++;
						}
	
					}
					int temp1 = count-2;
					int temp2 = count-1;
					out.println(temp1+"->"+temp2);
				}
			}
		}
		if(position > 0)
		{
			for(int j = 0;j<Labels.size();j++)
			{
				if(FinalAssoc.getLast().num2  == Labels.get(j).num){
					out.println(count+" " + "[label="+ Labels.get(j).Label +"];");
					count++;
				}
				
			}
			for(int j = 0;j<Labels.size();j++)
			{
				if(FinalAssoc.getLast().num1  == Labels.get(j).num){
					out.println(count+" " + "[label="+ Labels.get(j).Label +"];");
					count++;
				}
				
			}
			int temp1 = count -1;
			for(int k = 0;k<=position;k++)
			{
				int temp2 = count-2;
				out.println(temp1+"->"+temp2);
				count = count - 1;
			}
			count=count+position+1;
			position=0;
		}
		else
		{
			for(int j = 0;j<Labels.size();j++)
			{
				if(FinalAssoc.getLast().num1  == Labels.get(j).num){
					out.println(count+" " + "[label="+ Labels.get(j).Label +"];");
					count++;
				}

				if(FinalAssoc.getLast().num2  == Labels.get(j).num){
					out.println(count+" " + "[label="+ Labels.get(j).Label +"];");
					count++;
				}

			}
			int temp1 = count-2;
			int temp2 = count-1;
			out.println(temp1+"->"+temp2);
		}
	
		out.print("}");

		

		for(int i=0;i<FinalAssoc.size();i++){
			System.out.println(FinalAssoc.get(i).num1+"   "+FinalAssoc.get(i).num2);
		}

	}




	public static void Precedence(String S, LinkedList<node1> Labels){
		LinkedList<node2> Prec = new LinkedList<node2>();
		LinkedList<node3> FinalPrec = new LinkedList<node3>();
		LinkedList<node4> Finallist = new LinkedList<node4>();

		S = S.replaceAll("\\s","");
		S = S.replace("(","");
		S = S.replace(")","");
		//S = S.replace("}","");

		//Precedence-> Scan and insert each element individually
		int l =3;
		while(l<S.length()){
			node2 obj2 = new node2();
			if(!S.substring(l+1,l+2).equals(",") && !S.substring(l+1,l+2).equals("}") ){
				String conc = S.substring(l, l+1)+S.substring(l+1,l+2);
				obj2.num1 = Integer.parseInt(conc);
				Prec.add(obj2);
				l=l+3;
			}
			else{
				obj2.num1 = Integer.parseInt(S.substring(l,l+1));
				Prec.add(obj2);
				l = l+2;
			}
		}

		//Precedence->Copy the elements from the above list and insert them into a new list in the input format

		for (int i = 0;i<Prec.size();i=i+2){
			node3 obj3 = new node3();
			obj3.num1 = Prec.get(i).num1;
			obj3.num2 = Prec.get(i+1).num1;
			FinalPrec.add(obj3);
		}
		for (int i = 0;i<FinalPrec.size();i++){
			//System.out.println(FinalPrec.get(i).num1+","+FinalPrec.get(i).num2);
		}

		for(int i = 0;i<FinalPrec.size();i++){
			for(int j = 0;j<Labels.size();j++){
				if(FinalPrec.get(i).num1==Labels.get(j).num){
					node4 obj4 = new node4();
					obj4.num3 = Labels.get(j).num;
					obj4.FLabel = Labels.get(j).Label;
					Finallist.add(obj4);

				}
			}
		}

		for (int i = 0;i<Finallist.size();i++){
			//System.out.println(Finallist.get(i).num3+Finallist.get(i).FLabel);
		}


	}

}

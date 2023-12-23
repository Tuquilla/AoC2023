package day19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class day19 {

    public static void main(String[] args) throws IOException {
	
	BufferedReader bf = new BufferedReader(new FileReader("input.txt"));
	String line = bf.readLine();
	
	HashMap<String, LinkedHashMap<String, String>> workflows = new HashMap<String, LinkedHashMap<String, String>>();
	
	boolean switcher = false;
	int totalResult = 0;
	while (line != null) {
	    if (line.length() == 0) {
		switcher = true;
	    }
	    if (switcher == false) {
		    System.out.println(line);
		    //
		    String workflowName = line.split("\\{")[0];
		    String teilWorkflow = line.split("\\{")[1].split("\\}")[0];
		    
		    LinkedHashMap<String, String> workflowHashMap = new LinkedHashMap<>();
		    String[] instruktion = teilWorkflow.split("\\,");
		    
		    for (int i = 0; i < instruktion.length-1; i++) {
			String[] teilInstruktion = instruktion[i].split("\\:");
			workflowHashMap.put(teilInstruktion[0], teilInstruktion[1]);
		    }
		    
		    workflowHashMap.put("default", instruktion[instruktion.length-1]);
		    
		    workflows.put(workflowName, workflowHashMap);
		    
		    System.out.println(workflowName);
		    System.out.println(teilWorkflow);
		    

	    }
	    else {
		if (line.length() != 0) {
			String xmas = line.substring(1, line.length()-1);
			String[] xmasValues = xmas.split("\\,");
			if (workFlow(workflows, "in", xmasValues).equals("A")) {
			    System.out.println("A");
			    for (int i = 0; i < xmasValues.length; i++) {
				totalResult += Integer.parseInt(xmasValues[i].split("\\=")[1]);
			    }
			}
			//if (workFlow(workflows, in, ))   
		}
	    }
	    line = bf.readLine();

	}
	
	//Test
	/*
	for (Map.Entry<String, HashMap<String, String>> entry : workflows.entrySet()) {
	    for (Map.Entry<String, String> entryII : entry.getValue().entrySet()) {
		System.out.print(entryII.getKey() + " ");
		System.out.println(entryII.getValue());
	    }
	    System.out.println();
	}
	*/
	
	System.out.println("Lösung PartI: " + totalResult);

    }
    
    public static String workFlow(HashMap<String, LinkedHashMap<String, String>> workflows, String workflowName, String[] xmas) {
	System.out.println("WorkflowName: " + workflowName);
	if (!workflows.containsKey(workflowName)) {
	    return workflowName;
	}
	else {
	    for (Map.Entry<String, String> entry : workflows.get(workflowName).entrySet()) {
	    	if (entry.getKey().charAt(1) == '>' || entry.getKey().charAt(1) == '<') { 
			if (entry.getKey().charAt(1) == '>') {
			    for (int i = 0; i < xmas.length; i++) {
		    		if (xmas[i].charAt(0) == entry.getKey().charAt(0) && Integer.parseInt(xmas[i].substring(2, xmas[i].length())) > Integer.parseInt(entry.getKey().substring(2, entry.getKey().length()))) {
		    		    return workFlow(workflows, entry.getValue(), xmas);
		    		}
			    }

			}
			if (entry.getKey().charAt(1) == '<') {
			    for (int i = 0; i < xmas.length; i++) {
		    		if (xmas[i].charAt(0) == entry.getKey().charAt(0) && Integer.parseInt(xmas[i].substring(2, xmas[i].length())) < Integer.parseInt(entry.getKey().substring(2, entry.getKey().length()))) {
		    		    return workFlow(workflows, entry.getValue(), xmas);
		    		}
			    }

			}
	    	}
	    	else {
	    	    System.out.println("// " + entry.getValue());
	    	    return workFlow(workflows, entry.getValue(), xmas);
	    	}
	    }
	}
	return workflowName;
    }
     
}

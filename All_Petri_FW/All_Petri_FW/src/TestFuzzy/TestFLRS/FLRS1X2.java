package TestFuzzy.TestFLRS;

import java.util.ArrayList;
import Components.Activation;
import Components.Condition;
import Components.GuardMapping;
import Components.PetriNet;
import Components.PetriNetWindow;
import Components.PetriTransition;
import DataObjects.DataFuzzy;
import DataOnly.FLRS;
import DataOnly.FV;
import DataOnly.Fuzzy;
import DataOnly.FuzzyVector;
import DataOnly.PlaceNameWithWeight;
import Enumerations.FZ;
import Enumerations.LogicConnector;
import Enumerations.TransitionCondition;
import Enumerations.TransitionOperation;

public class FLRS1X2 {
	public static void main (String []args) {
		
		FLRS flrs1x2 = new FLRS(new FV(FZ.PL,FZ.NL), new FV(FZ.PM,FZ.NM), new FV(FZ.ZR,FZ.ZR), new FV(FZ.NL,FZ.PM),new FV(FZ.ZR,FZ.PL));
					

		flrs1x2.Print();
		
		PetriNet pn = new PetriNet();
		pn.PetriNetName = "Main Petri";
		pn.NetworkPort = 1081;

		DataFuzzy p1 = new DataFuzzy();
		p1.SetName("P1");
		p1.SetValue(new Fuzzy(0.1F));
		pn.PlaceList.add(p1);

		DataFuzzy p2 = new DataFuzzy();
		p2.SetName("P2");
		pn.PlaceList.add(p2);

		DataFuzzy p3 = new DataFuzzy();
		p3.SetName("P3");
		pn.PlaceList.add(p3);
		
		// T1 ------------------------------------------------
				PetriTransition t1 = new PetriTransition(pn);
				t1.TransitionName = "T1";
				t1.InputPlaceName.add("P1");

				Condition T1Ct1 = new Condition(t1, "P1", TransitionCondition.NotNull);

				GuardMapping grdT1 = new GuardMapping();
				grdT1.condition = T1Ct1;

				ArrayList<PlaceNameWithWeight> input = new ArrayList<>();
				input.add(new PlaceNameWithWeight("P1", 1F));

				
				ArrayList<String> Output = new ArrayList<>();
				Output.add("P2");
				Output.add("P3");

				
				grdT1.Activations.add(new Activation(t1, flrs1x2, input, TransitionOperation.FLRS, Output));
				
				t1.GuardMappingList.add(grdT1);

				t1.Delay = 0;
				pn.Transitions.add(t1);

				// -------------------------------------------

				System.out.println("Exp1 started \n ------------------------------");
				pn.Delay = 3000;
				// pn.Start();

				PetriNetWindow frame = new PetriNetWindow(false);
				frame.petriNet = pn;
				frame.setVisible(true);
		
	}

}

package Train;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

public class TrainTest {
	
	@Test
	public void testprintTrainABogiesToHyb() {
		
		Train.trainABogies = Arrays.asList("ENGINE", "NDL", "NDL", "KRN", "GHY", "SLM", "NJP", "NGP", "BLR");
		Train.trainBBogies = Arrays.asList("ENGINE", "NJP", "GHY", "AGA", "PNE", "MAO", "BPL", "PTA");
		LinkedList<String> trainAToHyd = Train.printTrainABogiesToHyb();
		boolean flag = trainAToHyd.contains("GHY");
		assertTrue(flag);
	    
	}
	@Test
	public void testprintTrainBBogiesToHyb() {
		
		Train.trainABogies = Arrays.asList("ENGINE", "NDL", "NDL", "KRN", "GHY", "SLM", "NJP", "NGP", "BLR");
		Train.trainBBogies = Arrays.asList("ENGINE", "NJP", "GHY", "AGA", "PNE", "MAO", "BPL", "PTA");
		LinkedList<String> trainAToHyd = Train.printTrainABogiesToHyb();
		boolean flag = trainAToHyd.contains("SRR");
		assertFalse(flag);
	    
	}
	@Test
	public void testprintTrainABogiesToHyb_CaseInValid() {
		
		Train.trainABogies = Arrays.asList("ENGINE", "NDL", "NDL", "KRN", "GHY", "SLM", "NJP", "NGP", "BLR");
		Train.trainBBogies = Arrays.asList("ENGINE", "NJP", "GHY", "AGA", "PNE", "MAO", "BPL", "PTA");
		LinkedList<String> trainAToHyd = Train.printTrainABogiesToHyb();
		boolean flag = trainAToHyd.contains("CHN");
		assertFalse(flag);
	    
	}
	@Test
	public void testDepartedFromHyb_InvalidCase() {
		
		Train.trainABogies = Arrays.asList("ENGINE", "NDL", "NDL", "KRN", "GHY", "SLM", "NJP", "NGP", "BLR");
		Train.trainBBogies = Arrays.asList("ENGINE", "NJP", "GHY", "AGA", "PNE", "MAO", "BPL", "PTA");
		 LinkedList<String> trainABfromoHyd =Train.trainDepartedFromHyb();
		boolean flag = trainABfromoHyd.contains("HYB");
		assertFalse(flag);
	    
	}
	@Test
	public void testDepartedFromHyb_LastStationTest() {
		
		Train.trainABogies = Arrays.asList("ENGINE", "NDL", "NDL", "KRN", "GHY", "SLM", "NJP", "NGP", "BLR");
		Train.trainBBogies = Arrays.asList("ENGINE", "NJP", "GHY", "AGA", "PNE", "MAO", "BPL", "PTA");
		 LinkedList<String> trainBToHyd = Train.printTrainBBogiesToHyb();
		assertEquals(trainBToHyd.getLast(),"PTA");
	    
	}
	@Test
	public void testDepartedFromHyb_TrainAB() {
		
		Train.trainABogies = Arrays.asList("ENGINE", "NDL", "NDL", "KRN", "GHY", "SLM", "NJP", "NGP", "BLR");
		Train.trainBBogies = Arrays.asList("ENGINE", "NJP", "GHY", "AGA", "PNE", "MAO", "BPL", "PTA");
		 LinkedList<String> trainAToHyd = Train.printTrainABogiesToHyb();
		 LinkedList<String> trainBToHyd = Train.printTrainBBogiesToHyb();
		 LinkedList<String> trainABfromoHyd =Train.trainDepartedFromHyb();
		assertTrue(trainABfromoHyd.contains("TRAIN_AB"));
	}
	@Test
	public void testDepartedFromHyb_LastStationTest_TrainA() {
		
		Train.trainABogies = Arrays.asList("ENGINE", "NDL", "NDL", "KRN", "GHY", "SLM", "NJP", "NGP", "BLR");
		Train.trainBBogies = Arrays.asList("ENGINE", "NJP", "GHY", "AGA", "PNE", "MAO", "BPL", "PTA");
		 LinkedList<String> trainAToHyd = Train.printTrainABogiesToHyb();
		assertEquals(trainAToHyd.getLast(),"NGP");
	    
	}
	@Test
	public void testprintTrainABogiesToHyb_Valid() {
		
		Train.trainABogies = Arrays.asList("ENGINE", "NDL", "NDL", "KRN", "GHY", "SLM", "NJP", "NGP", "BLR");
		Train.trainBBogies = Arrays.asList("ENGINE", "NJP", "GHY", "AGA", "PNE", "MAO", "BPL", "PTA");
		
		 LinkedList<String> trainAToHyd = Train.printTrainABogiesToHyb();
		 LinkedList<String> trainABfromoHyd =Train.trainDepartedFromHyb();
		 
	    assertEquals(trainABfromoHyd.indexOf("ENGINE")+1, trainABfromoHyd.lastIndexOf("ENGINE"));
	}
}

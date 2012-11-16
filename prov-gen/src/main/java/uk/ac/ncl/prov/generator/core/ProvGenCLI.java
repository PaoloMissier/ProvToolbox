/**
 * 
 */
package uk.ac.ncl.prov.generator.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;

import org.apache.commons.io.FileUtils;
import org.openprovenance.prov.java.JProvUtility;
import org.openprovenance.prov.java.component4.Bundle;

import uk.ac.ncl.prov.gen.generator.Generator;

/**
 * @author paolo
 *
 */
public class ProvGenCLI {

	static String inputGraphFileName; 
	static String outputGraphFileName;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String noSeedSwitch = "-S";
		int SWITCH_STATE = 0;
		int OUTPUT_STATE = 1;

		java.util.Set<String> switches = new HashSet<String>();		
		switches.add(noSeedSwitch);

		ProvGenCLI pgen = new ProvGenCLI();

		if (args.length == 0)  { Usage(); return; }

		int state = SWITCH_STATE;
		boolean noSeed = false;
		for (String arg : args) {

			if (arg.startsWith("-")) {
				if (arg.equals(noSeedSwitch)) { 
					noSeed = true;
					System.out.println("noSeed = true");
				}
				else System.out.println("unexpected switch: "+arg);
			} else if (state != OUTPUT_STATE) {
				inputGraphFileName = arg;
				state = OUTPUT_STATE;
			} else {  // OUTPUT_STATE
				outputGraphFileName = arg;
			}
		}
		
		if (inputGraphFileName == null) {
			System.out.println("input file name expected");
			Usage();
			return;
		}
		if (outputGraphFileName == null) {
			outputGraphFileName =  inputGraphFileName+".exp";
		}

		System.out.println("Input graph: "+ inputGraphFileName);
		System.out.println("Input graph: "+ outputGraphFileName);

		pgen.processGraph(inputGraphFileName, outputGraphFileName, noSeed);
	}


	private void processGraph(String inputGraphFileName, String outputGraphFileName, boolean noSeed) {

		JProvUtility u = new JProvUtility();
		File inputF = new File(inputGraphFileName);
		File outputF = new File(outputGraphFileName);
		Bundle bundle;
		try {
			bundle = u.convertASNToJava(inputF.getAbsolutePath());

			Generator generator = new Generator(bundle);

			if (!noSeed) {
				generator.seed(true);
				System.out.println("seeding completed");
			}

			Bundle newBundle = generator.expand();
			System.out.println("expansion completed");

			String asn = u.convertJavaToASN(newBundle);
			System.out.println("conversion completed");

			FileUtils.fileWrite(outputF.getAbsolutePath(), asn);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void Usage() {

		System.out.println("Usage: [-S] ProvGenCLI <input graph file> [<output graph file>] "+
		"\n -S means \"no seed\" and can be used when gen:concreteSaturation and gen:concreteCardinality are preset everywhere in your graph");
	}

}

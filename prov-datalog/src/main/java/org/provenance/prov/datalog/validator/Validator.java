package org.provenance.prov.datalog.validator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.unical.mat.dlv.program.Query;
import it.unical.mat.wrapper.DLVInputProgram;
import it.unical.mat.wrapper.DLVInputProgramImpl;
import it.unical.mat.wrapper.DLVInvocation;
import it.unical.mat.wrapper.DLVInvocationException;
import it.unical.mat.wrapper.DLVWrapper;
import it.unical.mat.wrapper.ModelHandler;
import it.unical.mat.wrapper.ModelResult;
import it.unical.mat.wrapper.Predicate;
import it.unical.mat.wrapper.PredicateHandlerWithName;
import it.unical.mat.wrapper.PredicateResult;

public class Validator {

	 static String docEditPublish = "${HOME}/Documents/Provenance-work/ProvToolbox/datalog/src/main/resources/IPAW12/EDB-doc-edit-publish.txt";
	 static String DLV_PATH="/Users/paolo/packages/DLV/dlv";
	 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/* I create a new instance of DLVInputProgram */
		DLVInputProgram inputProgram=new DLVInputProgramImpl();

		inputProgram.addFile(docEditPublish);
		
		DLVInvocation invocation=DLVWrapper.getInstance().createInvocation(DLV_PATH);
		
		try {
			// invocation setup
			invocation.setInputProgram(inputProgram);
			invocation.setMaxint(1000);
			invocation.setNumberOfModels(0);

			// define handlers
			final List <ModelResult> models= new ArrayList<ModelResult>();
			
			ModelHandler modelHandler=new ModelHandler(){
				final public void handleResult(DLVInvocation obsd, ModelResult res) {
					models.add(res);
				}
			};

			/* Subscribe the handler from the DLVInvocation */
			invocation.subscribe(modelHandler);

			/* I create a new observer that receive a notification for the predicates computed with a specified name and store them in a list */
			final List <PredicateResult> predicates= new ArrayList<PredicateResult>();
			PredicateHandlerWithName predicateHandler=new PredicateHandlerWithName() {
				public void handleResult(DLVInvocation obsd, PredicateResult res) {
					predicates.add(res);
				}
				
				public List <String> getPredicateNames() {
					List <String> predicates=new ArrayList<String>();
					predicates.add( "precedes");
					return predicates;
					}
			};
			/* Subscribe the handler from the DLVInvocation */
			invocation.subscribe(predicateHandler);

			
			// start computing
			invocation.run();

			invocation.waitUntilExecutionFinishes();

//			// look at results
//			for (ModelResult m in models) {
//				
//			}
//			while(model.hasMorePredicates()){
//				Predicate predicate=modelHandler.nextPredicate();
//			}

		} catch (DLVInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

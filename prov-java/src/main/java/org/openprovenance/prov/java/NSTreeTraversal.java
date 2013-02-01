/**
 * 
 */
package org.openprovenance.prov.java;

import java.util.List;

import org.antlr.runtime.tree.Tree;
import org.openprovenance.prov.notation.PROV_NParser;
import org.openprovenance.prov.notation.TreeConstructor;
import org.openprovenance.prov.notation.TreeTraversal;

/**
 * @author paolo
 *
 */
public class NSTreeTraversal extends TreeTraversal {

	private JavaConstructor c;


	public NSTreeTraversal(JavaConstructor c) {
		super(c);
	}



}

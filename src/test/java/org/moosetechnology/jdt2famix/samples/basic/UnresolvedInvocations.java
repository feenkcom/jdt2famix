package org.moosetechnology.jdt2famix.samples.basic;

import java.util.ArrayList;

public class UnresolvedInvocations {
	public void unresolvedClassInstantiation() {
		new ArrayList<EmptyClass>();
	}
}

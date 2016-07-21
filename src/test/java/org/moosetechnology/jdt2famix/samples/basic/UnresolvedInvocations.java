package org.moosetechnology.jdt2famix.samples.basic;

import java.util.ArrayList;

public class UnresolvedInvocations {
	public void unresolvedClassInstantiationArrayList() {
		new ArrayList<EmptyClass>();
	}

	public void unresolvedClassInstantiationToEmptyClass() {
		new EmptyClass();
	}
}

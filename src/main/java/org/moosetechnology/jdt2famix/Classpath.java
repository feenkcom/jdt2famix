package org.moosetechnology.jdt2famix;

import java.util.ArrayList;
import java.util.Collection;

public class Classpath {
	private Collection<String> classpath;

	public Classpath() {
		classpath = new ArrayList<String>();
		classpath.add(".");
	}
	
	public String[] paths() {
		return classpath.toArray(new String[0]);
	}
}

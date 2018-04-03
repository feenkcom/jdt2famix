package com.feenk.jdt2famix.samples.basic;

public class ClassWithComplexMethods {
	
	public void complexMethod1() {
		
	}
	
	public void complexMethod2() {
		int x = 5;
		x = x >= 3 ? x+2 : x; 
	}
	
	public void complexMethod3() {
		int x = 10; 
		if(x < 5) { } 
		
		do {
			x--; 
		}while(x > 3);
	}
	
	public void complexMethod4() {
		int x = 4; 
		switch(x) {
		 case 1: break; 
		 case 2: break;
		}
		while(x > 5) {}
	}

	public void complexMethod5() {
		int x = 4; 
		switch(x) {
		 case 1: break; 
		 case 2: break;
		}
		while(x > 5) {}
		int arr[] = {1,2,3,4,5};
		for(int v : arr) {} 
	}
	
	public void complexMethod6() {
		int x = 4; 
		switch(x) {
		 case 1: break; 
		 case 2: break;
		 default: break; 
		}
		while(x > 5) {}
		int arr[] = {1,2,3,4,5};
		for(int v : arr) {} 
	}
}

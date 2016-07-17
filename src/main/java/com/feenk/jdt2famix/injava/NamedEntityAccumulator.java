package com.feenk.jdt2famix.injava;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ch.akuhn.fame.Repository;


public class NamedEntityAccumulator<T> {
	
	/**
	 * We keep a reference to the shared Fame repository because we want to forward an entity to the repository
	 */
	private Repository repository;

	private Map<String,T> entities;
	
	public NamedEntityAccumulator(Repository repository) {
		this.repository = repository;
		entities = new HashMap<String,T>();
	}
	
	public List<T> get() { 
		return entities
			.entrySet()
			.stream()
			.map(p -> p.getValue())
			.collect(Collectors.toList());
	}
	
	public Stream<T> stream() {
		return entities
				.entrySet()
				.stream()
				.map(p -> p.getValue());
	}
	
	public void add(String packageName, T namespace) {
		entities.put(packageName, namespace);
		repository.add(namespace);
	}
	public T named(String qualifiedName) {
		return entities.get(qualifiedName);
	}
	public boolean has(String qualifiedName) {
		return entities.containsKey(qualifiedName);
	}

	public int size() {
		return entities.size();
	}

	public boolean isEmpty() {
		return entities.isEmpty();
	}

}

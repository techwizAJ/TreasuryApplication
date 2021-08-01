package com.pankhudi.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface DaoServices<T> {

	List<T> getAllobjects();
	void saveObject(T objects);
	T getPkObjectId(int id);
	void deleteObjectByPkId(int id);
	
	// Should be overridden by service implementation which require object by name
	default T getObjectByName(String name) {
		return null;
	}
}

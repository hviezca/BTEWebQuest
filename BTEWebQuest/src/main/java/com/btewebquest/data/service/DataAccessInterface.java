package com.btewebquest.data.service;

import java.util.List;

/**
 * Generic interface for handling all object database CRUD actions
 * 
 * @author sfradet
 *
 * @param <T> object to be maintained with interface
 */
public interface DataAccessInterface<T>{

	/**
	 * Return a List of all Objects from database
	 * 
	 * @return List of Objects
	 */
	public List<T> findAll();
	
	/**
	 * Return Object from database by id number
	 * 
	 * @param id id number of Object
	 * @return Object instance
	 */
	public T findById(int id);
	
	/**
	 * Create new Object in database
	 * 
	 * @param t Object to be added to database
	 * @return boolean indicating success of operation
	 */
	public boolean create(T t);
	
	/**
	 * Update Object in database
	 * 
	 * @param t Object to be updated in database
	 * @return boolean indicating success of operation
	 */
	public boolean update(T t);
	
	/**
	 * Delete Object in database
	 * 
	 * @param t Object to be deleted from database
	 * @return boolean indicating success of operation
	 */
	public boolean delete(T t);
}

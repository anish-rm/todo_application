package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();

	private static int todosCount = 0;
	
	// to initailize static
	static {
		todos.add(new Todo(++todosCount, "Anish", "Learn Spring", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "Anish", "Learn Devops", LocalDate.now().plusYears(3), false));
		todos.add(new Todo(++todosCount, "Anish", "Learn FullStack", LocalDate.now().plusYears(2), false));	
	}
	
	// to return
	public List<Todo> findByUsername(String username){
		// getting todos of specific username
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList(); 
	}
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todosCount,username,description,targetDate,done);
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		// if todo.getId() == id we want to remove that todo
		
		//we have to write lambda function
		//todo -> todo.getId() == id
		
		// we are just checking if there is id with this id if yes it remove that todo
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}
	
	public Todo findById(int id) {
		
		//to get specif todo base on id
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;

		//functional programming
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		
		// traditional
//		for(Todo todo:todos) {
//			if(id == todo.getId()) {
//				return todo;
//			}
//		}
		System.out.println(todo);
		return todo;
	}
	
	public void updateTodo(Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
	

}

package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("mname")
public class TodoControllerJpa {
	
	
	// jpa respository
	private TodoRepository todoRepository;
	// for autowiring
	
	public TodoControllerJpa(TodoService todoService, TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}


	// /list-todos
	
	@RequestMapping("/list-todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedInUsername();
		
//		todoRepository.getById(1)
		// we have updated from todoservice to todoRepository
		List<Todo> todos = todoRepository.findByUsername(username);
		
		model.addAttribute("todos",todos);
		
		return "listTodos";
	}



	
	//NO CHANGES BECOZ WE ARE NOT USING SERVICE HERE
	
	// when the url hit we need to show add todo page
	@RequestMapping(value="/add-todo",method= RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		
		// when add-todo is called this method is called
		// this method must set an attribute called todo into the model
		String username = getLoggedInUsername();
		Todo todo = new Todo(0,username, "default", LocalDate.now().plusYears(1),false);
		//these values will be bound to form now
		model.put("todo", todo); //"todo" this name should be same as what we use in form in todo.jsp 
		return "todo";
	}
	

	@RequestMapping(value="/add-todo",method= RequestMethod.POST)
	public String addNewTodo( ModelMap model, @Valid Todo todo, BindingResult result) {
	
		if(result.hasErrors()) {
			//but we have to show error in page so goto jsp
			return "todo";
		}
		
		String username = getLoggedInUsername();
		todo.setUsername(username);// why setting username
		// becoz todoRepository don't have method to take any attributes it take only object
		todoRepository.save(todo);
		return "redirect:list-todos";
	
	}
	
	@RequestMapping("/delete-todo")
	public String deleteTodo(@RequestParam int id, ModelMap model) {
		// Delete todo
		
		todoRepository.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="/update-todo",method= RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id, ModelMap model) {
		// we are just getting that todo and updating
//		Todo todo = TodoService.getTodo(id);
		Todo todo = todoRepository.findById(id).get(); // get becoz findById return optional from that too get todo put get()
		model.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value="/update-todo",method= RequestMethod.POST)
	public String updateTodo( ModelMap model, @Valid Todo todo, BindingResult result) {
	
		if(result.hasErrors()) {
			//but we have to show error in page so goto jsp
			return "todo";
		}
		
		String username = getLoggedInUsername();
		todo.setUsername(username); 
		todoRepository.save(todo);// it will update
		return "redirect:list-todos";
	
	}
	
	private String getLoggedInUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}


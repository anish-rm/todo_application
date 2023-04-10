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

//@Controller if we comment it our it will ignored by spring framework
@SessionAttributes("mname")
public class TodoController {
	
	private TodoService todoService;
	
	// for autowiring
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}



	// /list-todos
	
	@RequestMapping("/list-todos")
	public String listAllTodos(ModelMap model) {
		String username = getLoggedInUsername(model);
		List<Todo> todos = todoService.findByUsername(username);
		
		model.addAttribute("todos",todos);
		
		return "listTodos";
	}



	
	
	// when the url hit we need to show add todo page
	@RequestMapping(value="/add-todo",method= RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		
		// when add-todo is called this method is called
		// this method must set an attribute called todo into the model
		String username = getLoggedInUsername(model);
		Todo todo = new Todo(0,username, "default", LocalDate.now().plusYears(1),false);
		//these values will be bound to form now
		model.put("todo", todo); //"todo" this name should be same as what we use in form in todo.jsp 
		return "todo";
	}
	
	// after submitting we need to redirect it listTodos page
//	@RequestMapping(value="/add-todo",method= RequestMethod.POST)
//	public String addNewTodo(@RequestParam String Description, ModelMap model) {
////		List<Todo> todos = todoService.findByUsername("anish");
////		
////		model.addAttribute("todos",todos);
//		
//		//we can put like above but ot of duplicate
//		// so what we need to do is to redirect to that controller that handling list-todos so there is already todos popultaed and sent in modal
//		
//		// name is avaliable in session 
//		String username = (String)model.get("name");
//		todoService.addTodo(username, todo.getDescription(), LocalDate.now().plusYears(1), false);
//		return "redirect:list-todos";
//		// now t will go to this url and listtodos cntroller will handle this it will populate all items
//	
//	}
	
	
	//------------SPRING VALIDATION-------------
	//Currently we are reading description using Requestparam but 
	//suppose if we want to get 10 fields value then 10 Requestparam it would be not nice
	
	// instead we can directly bind to todo bean
	// this is command bean(backing object)
	@RequestMapping(value="/add-todo",method= RequestMethod.POST)
	public String addNewTodo( ModelMap model, @Valid Todo todo, BindingResult result) {
		// we have added validation in toda.java so to validate add @Valid before Todo
		// now if we add desc < 10 error but not like console error
		// we want it to be neatly in ui
		// useBindingResult so if error it will got this
		
		//so we can check if error , true => we need to stay in this page
	
		if(result.hasErrors()) {
			//but we have to show error in page so goto jsp
			return "todo";
		}
		
		String username = getLoggedInUsername(model);
		todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
		return "redirect:list-todos";
	
	}
	
	@RequestMapping("/delete-todo")
	public String deleteTodo(@RequestParam int id, ModelMap model) {
		// Delete todo
		
		todoService.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping(value="/update-todo",method= RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id, ModelMap model) {
		// we are just getting that todo and updating
//		Todo todo = TodoService.getTodo(id);
		Todo todo = todoService.findById(id);
		model.addAttribute("todo", todo);
		return "todo";
	}

	@RequestMapping(value="/update-todo",method= RequestMethod.POST)
	public String updateTodo( ModelMap model, @Valid Todo todo, BindingResult result) {
	
		if(result.hasErrors()) {
			//but we have to show error in page so goto jsp
			return "todo";
		}
		
		String username = getLoggedInUsername(model);
		todo.setUsername(username);
		todoService.updateTodo(todo);
		return "redirect:list-todos";
	
	}
	
	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}


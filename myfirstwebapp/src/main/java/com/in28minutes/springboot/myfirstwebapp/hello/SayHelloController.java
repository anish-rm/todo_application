package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//we want spring to manage this so only @Controller
@Controller // -> we have to add this so spring identify as bean
public class SayHelloController {
	// by default when we return string spring mvc will look for view with that specific name
	//it will not return this string back as it is
	//so add @ResponseBody --> now it will return whatever below return as is to the browser
	
	//"say-hello" -> 'Hello! what are you learning today
	@RequestMapping("/say-hello")
	@ResponseBody // explanation above
	public String sayHello() {
		return "Hello! what are you learning today";
	}
	
	// to return html as response
	@RequestMapping("/say-hello-html")
	@ResponseBody // explanation above
	public String sayHelloHtml() {
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title> First </title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h1>My first html page by anish</h1>");
		sb.append("</body>");
		sb.append("</html>");
		
		return sb.toString();
	}
	
	// the above method is complex if we put lot of code
	// so we can use jsp- java server page (view technology)
	// we want to create sayHello.jsp and want our controller to redirect to this page
	
	// "say-hello-jsp" => sayHello.jsp
	
	//  we have to create all our jsp file in spring recommended folder
	// /src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
	// /src/main/resources/META-INF/resources/WEB-INF/jsp/welcome.jsp
	// /src/main/resources/META-INF/resources/WEB-INF/jsp/login.jsp
	// /src/main/resources/META-INF/resources/WEB-INF/jsp/todos.jsp
	
	//only name is changing so we need to make rest as constant in application properties
	
	@RequestMapping("/say-hello-jsp") // we don't want response body because it will not llok for view it will return it as string
//	@ResponseBody  no need this because it wont look for view it return below as string as it is
	public String sayHelloJsp () {
		// no need to mention file path it is automatically set for jsp in application.properties
		return "sayHello";
		// but it still wont work we need to add jar in pom.xml
		// <dependency>
		// <groupId>org.apache.tomcat.embed</groupId>
		// <artifactId>tomcat-embed-jasper</artifactId>
		// <scope>provided</scope>
		// </dependency>
		// add this to pom.xml
	}
	

	
	

}

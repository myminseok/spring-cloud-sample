package com.example.cloudnativespring;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {

	private final MyConfig menu;

	public MyController(MyConfig menu) {
		this.menu = menu;
	}

    @GetMapping("/")
    public String hello() {
        return "Hello World!";
    }


	@GetMapping("/menu")
	public String restaurant() {
		return String.format("Today's special is: %s", menu.getSpecial());
	}

	@GetMapping("/getSessionNotes")
	public List<String> getSessionNotes(HttpServletRequest request) {
		List<String> notes = (List<String>) request.getSession().getAttribute("NOTES");
		System.out.println("notes.size:"+notes.size());
		return notes;
	}

	@PostMapping("/addSessionNote")
	public void addSessionNote(@RequestBody String note, HttpServletRequest request) {
		List<String> notes = (List<String>) request.getSession().getAttribute("NOTES");

		if (notes == null) {
			notes = new ArrayList<>();
		}

		System.out.println("note:"+note);

		notes.add(note);
		request.getSession().setAttribute("NOTES", notes);

		notes = (List<String>) request.getSession().getAttribute("NOTES");
		System.out.println("notes.size:"+notes.size());
	}

	@PostMapping("/invalidateSession")
	public void invalidateSession(HttpServletRequest request) {
		request.getSession(false).invalidate();
	}



}
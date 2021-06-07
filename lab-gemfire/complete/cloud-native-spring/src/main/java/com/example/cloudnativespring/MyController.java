package com.example.cloudnativespring;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

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
	}

	@PostMapping("/invalidateSession")
	public void invalidateSession(HttpServletRequest request) {
		request.getSession(false).invalidate();
	}



}
package jp.co.todo.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class TodoService {

	@Inject
	TodoBean todoBean;

	private int no = 0;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	private void countUp() {
		this.no++;
	}

	List<TodoBean> todoList = new ArrayList<>();

	public TodoBean getTodoBean() {
		return todoBean;
	}

	public void setTodoBean(TodoBean todoBean) {
		this.todoBean = todoBean;
	}

	public List<TodoBean> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<TodoBean> todoList) {
		this.todoList = todoList;
	}

	public String regist() {
		this.countUp();
		TodoBean newTodo = new TodoBean();
		newTodo.setContent(this.getTodoBean().getContent());
		newTodo.setTitle(this.getTodoBean().getTitle());
		newTodo.setNo(this.getNo());
		newTodo.setFinished(false);
		this.getTodoList().add(newTodo);

		return "index";
	}

	public String finish(int finishNo) {
		System.out.println(finishNo + "click");
		List<TodoBean> newTodoList = this.getTodoList().stream()
		.map(todo -> checkFinish(todo, finishNo))
		.collect(Collectors.toList());

		this.setTodoList(newTodoList);

		return "index";
	}

	public String delete(int deleteNo) {

		List<TodoBean> newTodoList = this.getTodoList().stream()
		.filter(todo -> !((todo.getNo() == deleteNo) && todo.isFinished()))
		.collect(Collectors.toList());

		this.setTodoList(newTodoList);

		return "index";
	}

	private TodoBean checkFinish(TodoBean todo, int finishNo) {
		if(todo.getNo() == finishNo) {
			System.out.println(todo.getNo() + "一致！");
			todo.setFinished(true);
		}
		return todo;
	}



}

package jp.co.todo.sample;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class TodoBean {

	private String title = null;
	private String content = null;
	private int no = 0;
	private boolean finished = false;

	@PostConstruct
	public void postConstruct() {
		System.out.println("create:" + this.getTitle() + "/" + this.getContent() );
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

}

package jp.co.todo.sample;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * TodoBeanクラス。
 *
 * @Namedアノテーションを付けることで、CDI管理BeanとなりJavaEE上でDIされます。
 * 使用範囲に応じたスコープのアノテーションを付けることでBeanのライフサイクルが決まります。
 *
 * @RequestScoded
 * @ApplicationScoped
 * @SessionScoped
 *
 */
@Named
@RequestScoped
public class TodoBean {

	/** No */
	private int no = 0;

	/** タイトル */
	private String title = null;

	/** 内容 */
	private String content = null;

	/** 完了有無 */
	private boolean finished = false;

	/**
	 * コンストラクタ
	 * @PostCOnstructアノテーションを付けることでCDI初期化時(Bean生成後)に
	 * 呼ばれます
	 */
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

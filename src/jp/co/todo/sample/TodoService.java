package jp.co.todo.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Todoサービスクラス
 *
 * Todoの登録、完了、削除など管理を行うクラス
 * @ApplicationScopedを付けているため、サーバ起動時にインスタンスが生成されます。
 *
 */
@Named
@ApplicationScoped
public class TodoService {

	/**
	 * @Injectアノテーションを付けることでDIされます。
	 * new()をしなくともインスタンスが生成されます。
	 */
	@Inject
	TodoBean todoBean;

	/** TodoNo */
	private int no = 0;

	/** Todoリスト */
	List<TodoBean> todoList = new ArrayList<>();

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

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

	/**
	 * TodoNo採番
	 */
	private void countUp() {
		this.no++;
	}

	/**
	 * Todo登録処理
	 * @return 画面名
	 */
	public String regist() {
		// No更新
		this.countUp();

		// 新規Todoオブジェクト生成
		TodoBean newTodo = new TodoBean();
		newTodo.setContent(this.getTodoBean().getContent());
		newTodo.setTitle(this.getTodoBean().getTitle());
		newTodo.setNo(this.getNo());
		newTodo.setFinished(false);

		// Todoリストに追加
		this.getTodoList().add(newTodo);

		return "index";
	}

	/**
	 * Todo更新処理
	 * @param finishNo 完了No
	 * @return 画面名
	 */
	public String finish(int finishNo) {

		// for Debug
		System.out.println(finishNo + "click");

		// Noが一致するTodoをチェック
		List<TodoBean> newTodoList = this.getTodoList().stream()
				.map(todo -> checkFinish(todo, finishNo))
				.collect(Collectors.toList());

		// 更新後のTodoリストセット
		this.setTodoList(newTodoList);

		return "index";
	}

	/**
	 * Todo削除処理
	 * @param deleteNo 削除No
	 * @return 画面名
	 */
	public String delete(int deleteNo) {

		// filterで削除対象のTodoをリストから除く
		List<TodoBean> newTodoList = this.getTodoList().stream()
				.filter(todo -> !((todo.getNo() == deleteNo) && todo.isFinished()))
				.collect(Collectors.toList());

		// 削除後のTodoリストセット
		this.setTodoList(newTodoList);

		return "index";
	}

	/**
	 * Todo完了チェック
	 * @param todo Todo
	 * @param finishNo 完了No
	 * @return
	 */
	private TodoBean checkFinish(TodoBean todo, int finishNo) {
		// Noが一致する場合に完了とする。
		if (todo.getNo() == finishNo) {
			// for debug
			System.out.println(todo.getNo() + "一致！");
			todo.setFinished(true);
		}
		return todo;
	}

}

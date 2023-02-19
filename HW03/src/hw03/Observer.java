package hw03;

public interface Observer<T> {
	public void update(Observable<T> sender, T event);
}


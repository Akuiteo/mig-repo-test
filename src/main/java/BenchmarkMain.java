import java.io.IOException;

public class BenchmarkMain {

	public static void main(String[] args) throws IOException {

		GithubRestClientTests tests = new GithubRestClientTests();



		tests.shouldGetOneUserByLogin();



	}
}

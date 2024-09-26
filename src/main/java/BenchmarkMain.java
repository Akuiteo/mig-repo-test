import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class BenchmarkMain {

	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {

		GithubRestClientTests tests = new GithubRestClientTests();



		tests.shouldMoveAllPullRequestFromTo();



	}
}

package  org.kohsuke.github;

import java.io.IOException;

import org.kohsuke.github.authorization.AuthorizationProvider;
import org.kohsuke.github.connector.GitHubConnector;

public class CustomGitHub extends GitHub {

	CustomGitHub(String apiUrl, GitHubConnector connector, GitHubRateLimitHandler rateLimitHandler,
			GitHubAbuseLimitHandler abuseLimitHandler, GitHubRateLimitChecker rateLimitChecker,
			AuthorizationProvider authorizationProvider) throws IOException {
		super(apiUrl, connector, rateLimitHandler, abuseLimitHandler, rateLimitChecker, authorizationProvider);
	}




	public GHRepository getRepository() {
		return null;

	}


}

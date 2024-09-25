import java.io.IOException;

public interface ClientTests {

	public void initGithub() throws IOException;

	public void shouldGetPullRequests_givenId() throws IOException;

	public void shouldSearchPullrequest_givenTitle() throws IOException;

	public void shouldSearchPullrequest_givenState() throws IOException;

	public void shouldCreatePullrequest() throws IOException;

	public void shouldListBranchProtections_givenBranch() throws IOException;
	public void shouldLockBranch() throws IOException;

	public void shouldUnlockBranch() throws IOException;

	public void shouldAddAuthorizedUser() throws IOException;

	public void shouldGetUsers() throws IOException;

	public void shouldGetOneUserByLogin() throws IOException;

}

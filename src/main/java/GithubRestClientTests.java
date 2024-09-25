import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHBranchProtection;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHPullRequest;
import org.kohsuke.github.GHPullRequest.MergeMethod;
import org.kohsuke.github.GHRef;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;
import org.kohsuke.github.PagedSearchIterable;
import org.w3c.dom.Document;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.xml.sax.SAXException;

public class GithubRestClientTests implements ClientTests {

	private static final String DEVELOP = null;

	private GitHub client;

	public String REPO_NAME = "test-repo";
	public String MAIN_BRANCH = "main";
	public String PROTECTION_TEST_BRANCH = "protection-test-branch";
	public String SOURCE_MERGEABLE_BRANCH_NAME = "mergeable-test-branch";
	public String SOURCE_NON_MERGEABLE_BRANCH_NAME = "non-mergeable-test-branch";

	public GithubRestClientTests() throws IOException {
		super();
		this.initGithub();
	}

	public void initGithub() throws IOException {
		this.client = GitHub.connectToEnterprise("https://api.github.com", "akxcn",
				"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");
	}

	/* ***************************************** */
	/* PULL REQUESTS */
	/* ***************************************** */

	public void shouldGetPullRequests_givenId() throws IOException {
		System.out.println(">> BEGIN shouldGetPullRequests_givenId");
		int id = 1234;

		GHPullRequest pr = client.getRepository(REPO_NAME).getPullRequest(id);

		System.out.println("PULL REQUEST");
		System.out.println(pr.getNumber());
		System.out.println(pr.getNumber());
		System.out.println("END shouldGetPullRequests_givenId <<");
	}

	public void shouldSearchPullrequest_givenTitle() throws IOException {
		System.out.println(">> BEGIN shouldSearchPullrequest_givenTitle");
		String title = "Test";

		PagedSearchIterable<GHPullRequest> pullrequests = this.client.getRepository(REPO_NAME)//
				.searchPullRequests()//
				.titleLike(title)//
				.list();

		for (GHPullRequest req : pullrequests) {

			System.out.println("PULL REQUEST");
			System.out.println(req.getNumber());
		}
		System.out.println("END shouldSearchPullrequest_givenTitle <<");
	}

	public void shouldSearchPullrequest_givenState() throws IOException {
		System.out.println(">> BEGIN shouldSearchPullrequest_givenTitle");

		PagedSearchIterable<GHPullRequest> pullrequests = this.client.getRepository(REPO_NAME)//
				.searchPullRequests()//
				.isOpen()//
				.list();

		for (GHPullRequest req : pullrequests) {

			System.out.println("PULL REQUEST");
			System.out.println(req.getNumber());
		}
		System.out.println("END shouldSearchPullrequest_givenTitle <<");
	}

	public void shouldCreatePullrequest() throws IOException {
		System.out.println(">> BEGIN shouldCreatePullrequest");

		GHPullRequest req = this.client.getRepository(REPO_NAME)//
				.createPullRequest("Test title", SOURCE_MERGEABLE_BRANCH_NAME, MAIN_BRANCH,
						"Test description avec <strong>HTML</strong>");

		System.out.println("PULL REQUEST");
		System.out.println(req.getNumber());
		System.out.println("END shouldCreatePullrequest <<");
	}


	/* ***************************************** */
	/* RESTRICTIONS DE BRANCHES */
	/* ***************************************** */

	public void shouldListBranchProtections_givenBranch() throws IOException {

		GHBranchProtection protection = client.getRepository(REPO_NAME)//
		.getBranch(PROTECTION_TEST_BRANCH)//
		.getProtection();

		System.out.println("BRANCH PROTECTION");
		System.out.println("isLockEnabled");
		System.out.println(protection.getLockBranch().isEnabled());
		System.out.println("force push");
		System.out.println(protection.getAllowForcePushes().isEnabled());
		System.out.println("reviews");
		System.out.println(protection.getRequiredReviews().getRequiredReviewers());
	}


	public void shouldLockBranch() throws IOException {

		GHBranchProtection protection = client.getRepository(REPO_NAME)//
		.getBranch(PROTECTION_TEST_BRANCH)//
		.enableProtection()//
		.enable();

		System.out.println("BRANCH PROTECTION");
		System.out.println("isLockEnabled");
		System.out.println(protection.getLockBranch().isEnabled());
	}



	public void shouldGetVersionFromPom() throws IOException, SAXException, ParserConfigurationException{
		GHContent file = client.getRepository(REPO_NAME).getFileContent("pom.xml", MAIN_BRANCH);
		var factory = DocumentBuilderFactory.newDefaultInstance();

		var pomVersion = "";

		Document parse1 = factory.newDocumentBuilder().parse(file.read());

		var parse = (DocumentTraversal) parse1;
		var nodeIterator = parse.createNodeIterator(parse1.getDocumentElement(), NodeFilter.SHOW_ELEMENT, revisionFilter(), false);
		var node = nodeIterator.nextNode();
		if (node != null) {
			System.out.println("VERSION FROM POM");
			System.out.println(node.getTextContent());
		}
	}

	NodeFilter revisionFilter() {
		return (currentNode) -> {
			if (currentNode.getParentNode() != null && "properties".equals(currentNode.getParentNode().getNodeName()) && "revision".equals(currentNode.getNodeName())) {
				return NodeFilter.FILTER_ACCEPT;
			}
			return NodeFilter.FILTER_REJECT;
		};
	}


	public void shouldCreateReleaseBranch() throws IOException {
		GHBranch branch = client.getRepository(REPO_NAME).getBranch(MAIN_BRANCH);

		var refCreated = client.getRepository(REPO_NAME).createRef("refs/heads/release/"+new Random().nextInt(), branch.getSHA1());

		System.out.println("RELEASE BRANCH");
		System.out.println(refCreated.getRef());
	}


	public void shouldDeleteBranch() throws IOException {
		GHBranch branch = client.getRepository(REPO_NAME).getBranch(MAIN_BRANCH);

		GHRef refCreated = null;
		// Existe dans l'API Github : DELETE https://api.github.com/repos/Akuiteo/mig-repo-test/git/refs/heads/to-delete
		System.out.println("RELEASE BRANCH");
		System.out.println(refCreated.getRef());
	}

	public void shouldMoveAllPullRequestFromTo() throws IOException {

		client.getRepository(REPO_NAME).createRef("branch-to-move", "7be7a4ef58aa30eec9048e72422b7aed976a4e06");
		GHPullRequest pr =client.getRepository(REPO_NAME).createPullRequest("pr-tomove", "branch-to-move", "main", "body");
		pr.setBaseBranch("develop");

	}


	public void shouldUnlockBranch() throws IOException {

		client.getRepository(REPO_NAME)//
		.getBranch(PROTECTION_TEST_BRANCH)//
		.disableProtection();

		System.out.println("BRANCH PROTECTION");
		System.out.println("isLockEnabled");
	}


	public void shouldAddAuthorizedUser() throws IOException {

		GHBranchProtection protection = client.getRepository(REPO_NAME)//
		.getBranch(PROTECTION_TEST_BRANCH)//
		.getProtection();


		System.out.println("BRANCH PROTECTION");
		System.out.println("isLockEnabled");
	}


	public void shouldMergeBranchWithFF() throws IOException {
		GHPullRequest pr = client.getRepository(REPO_NAME).getPullRequest(123);
		pr.merge("merge with ff", "7be7a4ef58aa30eec9048e72422b7aed976a4e06", MergeMethod.REBASE);
	}

	/* ***************************************** */
	/* USERS */
	/* ***************************************** */


	public void shouldGetUsers() throws IOException {
		System.out.println(">> BEGIN shouldGetUsers");
		PagedIterable<GHUser> users = this.client.listUsers();
		System.out.println("END shouldGetUsers <<");
	}

	public void shouldGetOneUserByLogin() throws IOException {
		System.out.println(">> BEGIN shouldGeOnetUserByLogin");
		GHUser users = this.client.getUser("akxcn");
		System.out.println("END shouldGeOnetUserByLogin <<");
	}

}

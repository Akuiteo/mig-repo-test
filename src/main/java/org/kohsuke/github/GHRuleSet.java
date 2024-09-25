package org.kohsuke.github;

public class GHRuleSet extends GitHubInteractiveObject {


	private Long id;

	private String name;

	private String sourceType;

	private String source;

	private Enforcement enforcement;

	private String nodeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Enforcement getEnforcement() {
		return enforcement;
	}

	public void setEnforcement(Enforcement enforcement) {
		this.enforcement = enforcement;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public GHRuleSetBuilder updateRuleSet() {
		return new GHRuleSetBuilder(this);
	}

}

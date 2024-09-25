package org.kohsuke.github;

public class GHRuleSetBuilder {


	private GHRuleSet ruleSet;

	private Requester builder;

	public GHRuleSetBuilder(GHRuleSet ghRuleSet) {
		this.ruleSet = ghRuleSet;
		this.builder = ruleSet.root().createRequest();
	}


	public GHRuleSetBuilder id(Long id) {
		builder.with("id", id);
		return this;
	}

	public GHRuleSetBuilder name(String name) {
		builder.with("name", name);
		return this;
	}

	public GHRuleSetBuilder sourceType(String sourceType) {
		builder.with("source_type", sourceType);
		return this;
	}

	public GHRuleSetBuilder source(String source) {
		builder.with("source", source);
		return this;
	}

	public GHRuleSetBuilder enforcement(Enforcement enforcement) {
		builder.with("enforcement", enforcement.getValue());
		return this;
	}

	public GHRuleSetBuilder nodeId(String nodeId) {
		builder.with("node_id", nodeId);
		return this;
	}





}

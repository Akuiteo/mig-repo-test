package org.kohsuke.github;

import java.io.IOException;

public class CustomGHRepository extends GHRepository {


	public PagedIterable<GHRuleSet> getRuleSets(){
		 return root().createRequest()
	                .withUrlPath(getApiTailUrl("rulesets"))//
	                .toIterable(GHRuleSet[].class, null);
	}


	public GHRuleSet getRuleSet(Long id) throws IOException {
		return root().createRequest()//
				.withUrlPath(getApiTailUrl("rulesets/"+id))//
				.fetch(GHRuleSet.class);
	}



}

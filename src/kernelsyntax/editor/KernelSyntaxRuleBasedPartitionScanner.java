package kernelsyntax.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;

public class KernelSyntaxRuleBasedPartitionScanner extends RuleBasedPartitionScanner {

	public KernelSyntaxRuleBasedPartitionScanner(KernelSyntaxLang ksl) {
		List<IPredicateRule> rules = new ArrayList<IPredicateRule>();
		ksl.attachPartitionRule(rules);
		setPredicateRules(rules.toArray(new IPredicateRule[rules.size()]));
	}
	
}
package kernelsyntax.editor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.*;

public class KernelSyntaxRuleBasedScanner extends RuleBasedScanner {
	KernelSyntaxLang ksl;
	public KernelSyntaxRuleBasedScanner(KernelSyntaxLang ksl_new) {
		super();
		ksl = ksl_new;
		List<IRule> rules = new ArrayList<IRule>();
		rules.add(new WhitespaceRule(new KernelSyntaxWhitespaceDetector()));
		ksl.attachWordRule(rules);
		setRules(rules.toArray(new IRule[rules.size()]));
     }
}

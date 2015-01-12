package kernelsyntax.editor;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class KernelSyntaxOperatorRule implements IRule {
	private IToken token;
	public KernelSyntaxOperatorRule(IToken token) {
		this.token = token;
	}

	public boolean isRuleChar(int ch) {
		return (ch == ';' || ch == '.' || ch == ':' || ch == '=' || ch == '-'
				|| ch == '+' || ch == '\\' || ch == '!'
				|| ch == '%' || ch == '^' || ch == '&' || ch == '~'
				|| ch == '['|| ch == ']'|| ch == '('|| ch == ')'|| ch == '{'|| ch == '}'
				|| ch == '>' || ch == '<'|| ch == '|'); //  || ch == '*'
	}
	public IToken evaluate(ICharacterScanner scanner) {
		int ch = scanner.read();

		if (isRuleChar(ch)) {
			return token;
		}
		scanner.unread();
		return Token.UNDEFINED;
	}
}
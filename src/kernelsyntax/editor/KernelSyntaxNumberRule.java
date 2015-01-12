package kernelsyntax.editor;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class KernelSyntaxNumberRule implements IRule {

	private IToken token;
	public KernelSyntaxNumberRule(IToken token) {
		super();
		this.token = token;
	}

	public IToken evaluate(ICharacterScanner scanner) {
		int startCh = scanner.read();
		int ch;
		int unreadCount = 1;

		if (isNumberStart(startCh)) {
			ch = startCh;
			if (startCh == '-' || startCh == '+') {
				ch = scanner.read();
				++unreadCount;
			}
			if (ch == '0') {
				int xCh = scanner.read();
				++unreadCount;
				if (xCh == 'x' || xCh == 'X') {
					// hexnumber starting with [+-]?0[xX]
					do {
						ch = scanner.read();
					} while (isHexNumberPart((char) ch));
					scanner.unread();
					return token;
				}
				scanner.unread();
				// assert ch == '0';
			} else if (!Character.isDigit((char) ch)) {
				ch = scanner.read();
				++unreadCount;
			}
			if (Character.isDigit((char) ch)) {
				do {
					ch = scanner.read();
				} while (Character.isDigit((char) ch));
				if (ch == '.' && startCh != '.') {
					do {
						ch = scanner.read();
					} while (Character.isDigit((char) ch));
				}
				if (ch == 'e' || ch == 'E') {
					ch = scanner.read();
					if (ch == '-' || ch == '+' || Character.isDigit((char) ch)) {
						do {
							ch = scanner.read();
						} while (Character.isDigit((char) ch));
					}
				}
				if (ch == 'f' || ch == 'F') {
					// floot
					ch = scanner.read();
				}
				scanner.unread();
				return token;
			}
		}
		do {
			scanner.unread();
		} while (--unreadCount > 0);
		return Token.UNDEFINED;
	}

	private boolean isNumberStart(int ch) {
		return ch == '-' || ch == '+' || ch == '.'
				|| Character.isDigit((char) ch);
	}

	private boolean isHexNumberPart(int ch) {
		return Character.isDigit((char) ch) || ch == 'a' || ch == 'b'
				|| ch == 'c' || ch == 'd' || ch == 'e' || ch == 'f'
				|| ch == 'A' || ch == 'B' || ch == 'C' || ch == 'D'
				|| ch == 'E' || ch == 'F';
	}
}
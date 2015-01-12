package kernelsyntax.editors;


import java.util.List;

import kernelsyntax.editor.KernelSyntaxDamagerRepairer;
import kernelsyntax.editor.KernelSyntaxLang;
import kernelsyntax.editor.KernelSyntaxNumberRule;
import kernelsyntax.editor.KernelSyntaxOperatorRule;
import kernelsyntax.editor.KernelSyntaxWordDetector;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

public class KernelSyntaxLangPython extends KernelSyntaxLang {

	private final static String[] KSL_KEYWORD = {
		"and",
		"as",
		"assert",
		"break",
		"class",
		"continue",
		"def",
		"del",
		"elif",
		"else",
		"except",
		"exec",
		"finally",
		"for",
		"from",
		"global",
		"if",
		"import",
		"in",
		"is",
		"lambda",
		"not",
		"or",
		"pass",
		"print",
		"raise",
		"return",
		"try",
		"while",
		"with",
		"yield",
		
		"False", "None", "True",
	};
	
public void attachDamagerRepairer(PresentationReconciler pr) {
	System.out.println("pyth");
	
	KernelSyntaxDamagerRepairer ndr;
	
	ndr = new KernelSyntaxDamagerRepairer(new TextAttribute(KSC_COMMENT));
	pr.setDamager(ndr, KSP_COMMENT);
	pr.setRepairer(ndr, KSP_COMMENT);
    
	ndr = new KernelSyntaxDamagerRepairer(new TextAttribute(KSC_STRING));
	pr.setDamager(ndr, KSP_STRING);
	pr.setRepairer(ndr, KSP_STRING);
	
	ndr = new KernelSyntaxDamagerRepairer(new TextAttribute(KSC_METADATA));
	pr.setDamager(ndr, KSP_METADATA);
	pr.setRepairer(ndr, KSP_METADATA);
}

public void attachPartitionRule(List<IPredicateRule> rules) {
	IToken tokenComment = new Token(KSP_COMMENT);
	IToken tokenString = new Token(KSP_STRING);
	IToken tokenMetadata = new Token(KSP_METADATA);
	rules.add(new EndOfLineRule("@", tokenMetadata));
	rules.add(new EndOfLineRule("#", tokenComment));
	rules.add(new MultiLineRule("\"", "\"", tokenString, '\\', true));
	rules.add(new MultiLineRule("\'", "\'", tokenString, '\\', true));
	rules.add(new MultiLineRule("\"\"\"", "\"\"\"", tokenString, '\\', true));
	rules.add(new MultiLineRule("\'\'\'", "\'\'\'", tokenString, '\\', true));
}

public void attachWordRule(List<IRule> rules) {
	IToken tokenDefault = new Token(new TextAttribute(KSC_DEFAULT));
	IToken tokenKeyword = new Token(new TextAttribute(KSC_KEYWORD));
	IToken tokenNumber = new Token(new TextAttribute(KSC_NUMBER));
	IToken tokenOperator = new Token(new TextAttribute(KSC_OPERATOR));
	
	WordRule wordRule = new WordRule(new KernelSyntaxWordDetector(), tokenDefault, true);
	for (String keyword : KSL_KEYWORD) {
		wordRule.addWord(keyword, tokenKeyword);
	}
	rules.add(wordRule);
	
	KernelSyntaxNumberRule numberRule = new KernelSyntaxNumberRule(tokenNumber);
	rules.add(numberRule);
	
	KernelSyntaxOperatorRule opRule = new KernelSyntaxOperatorRule(tokenOperator);
	rules.add(opRule);
	}
}

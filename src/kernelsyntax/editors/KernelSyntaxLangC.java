package kernelsyntax.editors;


import java.util.List;

import kernelsyntax.editor.KernelSyntaxDamagerRepairer;
import kernelsyntax.editor.KernelSyntaxLang;
import kernelsyntax.editor.KernelSyntaxNumberRule;
import kernelsyntax.editor.KernelSyntaxOperatorRule;
import kernelsyntax.editor.KernelSyntaxWordDetector;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.BufferedRuleBasedScanner;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

public class KernelSyntaxLangC extends KernelSyntaxLang {

	public String[] KSPS = {KSP_COMMENT,KSP_STRING,KSP_MACRO};
	
	
	private static String[] KSL_KEYWORD = {
		"auto",
		"double",
		"int",
		"long",
		"break",
		"else",
		"long",
		"switch",
		"case",
		"enum",
		"register",
		"typedef",
		"char",
		"extern",
		"return",
		"union",
		"const",
		"float",
		"short",
		"unsigned",
		"continue",
		"for",
		"signed",
		"void",
		"default",
		"goto",
		"sizeof",
		"volatile",
		"do",
		"if",
		"static",
		"while",
		"bool"
	};
	
	/*
	private final static String[] KSL_OPERATOR = {
		"=","-","+","*","/",
		"(",")","<",">","[","]","{","}",
		"&","|","!","^",
		",",";",":","."
		// !%&@`~
	};
	*/
	class MyCommentScanner extends BufferedRuleBasedScanner {
		  private MyCommentScanner() {
		    IToken commentToken = new Token(new TextAttribute(KSC_COMMENT));
		    IRule[] rules = new IRule[] {
		      new MultiLineRule("/*","*/",commentToken),
		      new EndOfLineRule("//",commentToken),
		    };
		    setRules(rules);
		  }
		}
public void attachDamagerRepairer(PresentationReconciler pr) {
	KernelSyntaxDamagerRepairer ndr;
	DefaultDamagerRepairer ddr;
	ddr = new DefaultDamagerRepairer(new MyCommentScanner());
	// ndr = new KernelSyntaxDamagerRepairer(new TextAttribute(KSC_COMMENT));
	pr.setDamager(ddr, KSP_COMMENT);
	pr.setRepairer(ddr, KSP_COMMENT);
    
	ndr = new KernelSyntaxDamagerRepairer(new TextAttribute(KSC_STRING));
	pr.setDamager(ndr, KSP_STRING);
	pr.setRepairer(ndr, KSP_STRING);
	
	ndr = new KernelSyntaxDamagerRepairer(new TextAttribute(KSC_MACRO));
	pr.setDamager(ndr, KSP_MACRO);
	pr.setRepairer(ndr, KSP_MACRO);
}

	
public void attachPartitionRule(List<IPredicateRule> rules) {
	IToken tokenComment = new Token(KSP_COMMENT);
	IToken tokenString = new Token(KSP_STRING);
	IToken tokenMacro = new Token(KSP_MACRO);
	rules.add(new EndOfLineRule("//", tokenComment));
	rules.add(new EndOfLineRule("#", tokenMacro));
	rules.add(new MultiLineRule("/*", "*/", tokenComment, (char)0, true));
	rules.add(new MultiLineRule("\"", "\"", tokenString, '\\', true));
	rules.add(new MultiLineRule("\'", "\'", tokenString, '\\', true));
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

package kernelsyntax.editor;


import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;

public  class KernelSyntaxLang {

	public final static String KSP_COMMENT = "__ksp_comment";
	public final static String KSP_STRING = "__ksp_string";
	public final static String KSP_MACRO = "__ksp_macro";
	public final static String KSP_METADATA = "__ksp_metadata";

	public final static Color KSC_DEFAULT  = new Color(null, new RGB(0, 0, 0));
	public final static Color KSC_COMMENT  = new Color(null, new RGB(0, 128, 0));
	public final static Color KSC_STRING   = new Color(null, new RGB(163, 21, 21));
	public final static Color KSC_NUMBER   = new Color(null, new RGB(43, 145, 175));
	public final static Color KSC_KEYWORD  = new Color(null, new RGB(0, 0, 255));
	public final static Color KSC_LIBRARY  = new Color(null, new RGB(43, 145, 175));
	public final static Color KSC_METADATA = new Color(null, new RGB(43, 145, 175));
	public final static Color KSC_OPERATOR = new Color(null, new RGB(0, 64, 128));
	public final static Color KSC_MACRO    = new Color(null, new RGB(111, 0, 138));

	public String[] KSPS = {IDocument.DEFAULT_CONTENT_TYPE,KSP_COMMENT,KSP_STRING,KSP_MACRO,KSP_METADATA};
	
	
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
	
	public void attachDamagerRepairer(PresentationReconciler pr) {
		KernelSyntaxDamagerRepairer ndr;
		
		ndr = new KernelSyntaxDamagerRepairer(new TextAttribute(KSC_COMMENT));
		pr.setDamager(ndr, KSP_COMMENT);
		pr.setRepairer(ndr, KSP_COMMENT);
	    
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
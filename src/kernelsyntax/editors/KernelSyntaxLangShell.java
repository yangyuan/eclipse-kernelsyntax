package kernelsyntax.editors;


import java.util.List;

import kernelsyntax.editor.KernelSyntaxDamagerRepairer;
import kernelsyntax.editor.KernelSyntaxLang;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;

public class KernelSyntaxLangShell extends KernelSyntaxLang {

	public String[] KSPS = {KSP_COMMENT,KSP_STRING};
	
	
	private static String[] KSL_KEYWORD = {
		"$",
		"$#",
		"!",
		"[[",
		"]]",
		"{",
		"}",
		"case",
		"do",
		"done",
		"elif",
		"else",
		"esac",
		"fi",
		"for",
		"function",
		"if",
		"in",
		"select",
		"then",
		"time",
		"until",
		"while"
	};
	private static String[] KSL_OPERATOR = {
		"&",
		";",
		"|",
		">",
		">>",
		"&&",
		"||",
	};
	
	private static String[] KSL_LIBRARY = {
		"alias",
		"apropos",
		"apt-get",
		"aptitude",
		"aspell",
		"awk",
		"basename",
		"bash",
		"bc",
		"bg",
		"break",
		"builtin",
		"bzip2",
		"cal",
		"cat",
		"cd",
		"cfdisk",
		"chgrp",
		"chmod",
		"chown",
		"chroot",
		"chkconfig",
		"cksum",
		"clear",
		"cmp",
		"comm",
		"command",
		"continue",
		"cp",
		"cron",
		"crontab",
		"csplit",
		"cut",
		"date",
		"dc",
		"dd",
		"ddrescue",
		"declare",
		"df",
		"diff",
		"diff3",
		"dig",
		"dir",
		"dircolors",
		"dirname",
		"dirs",
		"dmesg",
		"du",
		"echo",
		"egrep",
		"eject",
		"enable",
		"env",
		"ethtool",
		"eval",
		"exec",
		"exit",
		"expect",
		"expand",
		"export",
		"expr",
		"false",
		"fdformat",
		"fdisk",
		"fg",
		"fgrep",
		"file",
		"find",
		"fmt",
		"fold",
		"format",
		"free",
		"fsck",
		"ftp",
		"function",
		"fuser",
		"gawk",
		"getopts",
		"grep",
		"groupadd",
		"groupdel",
		"groupmod",
		"groups",
		"gzip",
		"hash",
		"head",
		"help",
		"history",
		"hostname",
		"iconv",
		"id",
		"ifconfig",
		"ifdown",
		"ifup",
		"import",
		"install",
		"jobs",
		"join",
		"kill",
		"killall",
		"less",
		"let",
		"link",
		"ln",
		"local",
		"locate",
		"logname",
		"logout",
		"look",
		"lpc",
		"lpr",
		"lprint",
		"lprintd",
		"lprintq",
		"lprm",
		"ls",
		"lsof",
		"make",
		"man",
		"mkdir",
		"mkfifo",
		"mkisofs",
		"mknod",
		"more",
		"most",
		"mount",
		"mtools",
		"mtr",
		"mv",
		"mmv",
		"netstat",
		"nice",
		"nl",
		"nohup",
		"notify-send",
		"nslookup",
		"open",
		"op",
		"passwd",
		"paste",
		"pathchk",
		"ping",
		"pkill",
		"popd",
		"pr",
		"printcap",
		"printenv",
		"printf",
		"ps",
		"pushd",
		"pv",
		"pwd",
		"quota",
		"quotacheck",
		"quotactl",
		"ram",
		"rcp",
		"read",
		"readarray",
		"readonly",
		"reboot",
		"rename",
		"renice",
		"remsync",
		"return",
		"rev",
		"rm",
		"rmdir",
		"rsync",
		"screen",
		"scp",
		"sdiff",
		"sed",
		"select",
		"seq",
		"set",
		"sftp",
		"shift",
		"shopt",
		"shutdown",
		"sleep",
		"slocate",
		"sort",
		"source",
		"split",
		"ssh",
		"stat",
		"strace",
		"su",
		"sudo",
		"sum",
		"suspend",
		"sync",
		"tail",
		"tar",
		"tee",
		"test",
		"time",
		"timeout",
		"times",
		"touch",
		"top",
		"traceroute",
		"trap",
		"tr",
		"true",
		"tsort",
		"tty",
		"type",
		"ulimit",
		"umask",
		"umount",
		"unalias",
		"uname",
		"unexpand",
		"uniq",
		"units",
		"unset",
		"unshar",
		"until",
		"uptime",
		"useradd",
		"userdel",
		"usermod",
		"users",
		"uuencode",
		"uudecode",
		"v",
		"vdir",
		"vi",
		"vmstat",
		"wait",
		"watch",
		"wc",
		"whereis",
		"which",
		"while",
		"who",
		"whoami",
		"wget",
		"write",
		"xargs",
		"xdg-open",
		"yes",
		"zip",
		".",
		"!!",
		"###"
	};
	
	private static String[] KSL_FUNCTIONS = {
		"python",
		"screen"
	};
	
	public void attachDamagerRepairer(PresentationReconciler pr) {
		KernelSyntaxDamagerRepairer ndr;
		
		ndr = new KernelSyntaxDamagerRepairer(new TextAttribute(KSC_COMMENT));
		pr.setDamager(ndr, IDocument.DEFAULT_CONTENT_TYPE);
		pr.setRepairer(ndr, IDocument.DEFAULT_CONTENT_TYPE);
	
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
		//rules.add(new EndOfLineRule("#!", tokenMetadata));
		EndOfLineRule commentRule = new EndOfLineRule("#", tokenComment);
		commentRule.setColumnConstraint(0);
		rules.add(commentRule);
		rules.add(new EndOfLineRule(" #", tokenComment));
		rules.add(new EndOfLineRule("\t#", tokenComment));
		rules.add(new MultiLineRule("\"", "\"", tokenString, '\\', true));
		rules.add(new MultiLineRule("\'", "\'", tokenString, '\\', true));
		rules.add(new MultiLineRule("`", "`", tokenString, '\\', true));
	}
	
	public class ShellWordDetector implements IWordDetector {
		private String ShellUnIdentifier = " \t\n\r";
		
		@Override
		public boolean isWordPart(char c) {
			if (Character.isJavaIdentifierPart(c)) return true;
			if (ShellUnIdentifier.indexOf(c) < 0) {
				return true;
			}
			return false;
		}

		@Override
		public boolean isWordStart(char c) {
	        if (Character.isJavaIdentifierStart(c)) return true;
	        if (ShellUnIdentifier.indexOf(c) < 0) {
				return true;
			}
			return false;
		}

	}
	
	
	public void attachWordRule(List<IRule> rules) {
		IToken tokenDefault  = new Token(new TextAttribute(KSC_DEFAULT));
		IToken tokenKeyword  = new Token(new TextAttribute(KSC_KEYWORD));
		IToken tokenLibrary  = new Token(new TextAttribute(KSC_LIBRARY));
		IToken tokenOperator = new Token(new TextAttribute(KSC_OPERATOR));

		//IToken tokenComment = new Token(KSP_COMMENT);
		//rules.add(new EndOfLineRule("#", tokenComment));
		
		WordRule wordRule = new WordRule(new ShellWordDetector(), tokenDefault, true);
		for (String keyword : KSL_KEYWORD) {
			wordRule.addWord(keyword, tokenKeyword);
		}
		
		for (String keyword : KSL_LIBRARY) {
			wordRule.addWord(keyword, tokenLibrary);
		}
		
		for (String keyword : KSL_FUNCTIONS) {
			wordRule.addWord(keyword, tokenLibrary);
		}
		for (String keyword : KSL_OPERATOR) {
			wordRule.addWord(keyword, tokenOperator);
		}
		
		
		rules.add(wordRule);
	}
}

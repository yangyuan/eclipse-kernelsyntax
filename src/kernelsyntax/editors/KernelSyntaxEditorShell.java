package kernelsyntax.editors;

import kernelsyntax.editor.KernelSyntaxDocumentProvider;
import kernelsyntax.editor.KernelSyntaxLang;
import kernelsyntax.editor.KernelSyntaxSourceViewerConfiguration;

import org.eclipse.ui.editors.text.TextEditor;


public class KernelSyntaxEditorShell extends TextEditor {
	public KernelSyntaxEditorShell() {
		super();
	    KernelSyntaxLang ksl = new KernelSyntaxLangShell();
		setSourceViewerConfiguration(new KernelSyntaxSourceViewerConfiguration(ksl));
		setDocumentProvider(new KernelSyntaxDocumentProvider(ksl));
	}
}

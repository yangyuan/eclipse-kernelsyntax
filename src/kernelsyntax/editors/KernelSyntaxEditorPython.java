package kernelsyntax.editors;

import kernelsyntax.editor.KernelSyntaxDocumentProvider;
import kernelsyntax.editor.KernelSyntaxLang;
import kernelsyntax.editor.KernelSyntaxSourceViewerConfiguration;

import org.eclipse.ui.editors.text.TextEditor;


public class KernelSyntaxEditorPython extends TextEditor {
	public KernelSyntaxEditorPython() {
		super();
	    KernelSyntaxLang ksl = new KernelSyntaxLangPython();
		setSourceViewerConfiguration(new KernelSyntaxSourceViewerConfiguration(ksl));
		setDocumentProvider(new KernelSyntaxDocumentProvider(ksl));
	}
}

package kernelsyntax.editors;

import kernelsyntax.editor.KernelSyntaxDocumentProvider;
import kernelsyntax.editor.KernelSyntaxLang;
import kernelsyntax.editor.KernelSyntaxSourceViewerConfiguration;

import org.eclipse.ui.editors.text.TextEditor;


public class KernelSyntaxEditorC extends TextEditor {
	public KernelSyntaxEditorC() {
		super();
	    KernelSyntaxLang ksl = new KernelSyntaxLangC();
		setSourceViewerConfiguration(new KernelSyntaxSourceViewerConfiguration(ksl));
		setDocumentProvider(new KernelSyntaxDocumentProvider(ksl));
	}
}

package kernelsyntax.editor;

import kernelsyntax.editor.KernelSyntaxDocumentProvider;
import kernelsyntax.editor.KernelSyntaxLang;
import kernelsyntax.editor.KernelSyntaxSourceViewerConfiguration;

import org.eclipse.ui.editors.text.TextEditor;


public class KernelSyntaxEditor extends TextEditor {
	public KernelSyntaxEditor() {
		super();
	    KernelSyntaxLang ksl = new KernelSyntaxLang();
		setSourceViewerConfiguration(new KernelSyntaxSourceViewerConfiguration(ksl));
		setDocumentProvider(new KernelSyntaxDocumentProvider(ksl));
	}
}

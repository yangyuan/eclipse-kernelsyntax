package kernelsyntax.editor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.ui.editors.text.FileDocumentProvider;

public class KernelSyntaxDocumentProvider extends FileDocumentProvider  {
	KernelSyntaxLang ksl;
	public KernelSyntaxDocumentProvider(KernelSyntaxLang ksl_new) {
		super();
		ksl = ksl_new;
	}
	protected IDocument createDocument(Object element) throws CoreException {
		IDocument document = super.createDocument(element);
		if (document != null) {
			IDocumentPartitioner partitioner =
				new FastPartitioner(new KernelSyntaxRuleBasedPartitionScanner(ksl), ksl.KSPS);
			partitioner.connect(document);
			document.setDocumentPartitioner(partitioner);
		}
		return document;
	}
}

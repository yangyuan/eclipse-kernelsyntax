package kernelsyntax.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;


public class KernelSyntaxSourceViewerConfiguration extends SourceViewerConfiguration {
	KernelSyntaxTextDoubleClickStrategy doubleClickStrategy ;
	KernelSyntaxLang ksl;
	public KernelSyntaxSourceViewerConfiguration(KernelSyntaxLang ksl_new) {
		super();
		ksl = ksl_new;
	}
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
         PresentationReconciler pr = new PresentationReconciler();
         ksl.attachDamagerRepairer(pr);
         DefaultDamagerRepairer ddr = new DefaultDamagerRepairer(new KernelSyntaxRuleBasedScanner(ksl));
         pr.setRepairer(ddr, IDocument.DEFAULT_CONTENT_TYPE);
         pr.setDamager(ddr, IDocument.DEFAULT_CONTENT_TYPE);
         return pr;
	}
	
	@Override
    public int getTabWidth(ISourceViewer sourceViewer) {
		return 4 ;
    }
    
	public ITextDoubleClickStrategy getDoubleClickStrategy(ISourceViewer sourceViewer,String contentType) {
        if (doubleClickStrategy == null) doubleClickStrategy = new KernelSyntaxTextDoubleClickStrategy();
        return doubleClickStrategy;
    }
}

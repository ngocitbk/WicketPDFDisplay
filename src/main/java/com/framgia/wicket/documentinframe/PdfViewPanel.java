package com.framgia.wicket.documentinframe;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.ResourceReference;

public class PdfViewPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private ResourceReference pdfResource;

    private WebMarkupContainer wmc;

    public PdfViewPanel(String id) {
        super(id);
        wmc = new WebMarkupContainer("myPdfPanel");
        if (pdfResource != null) {
            wmc.add(new AttributeModifier("src", (String) urlFor(pdfResource, null)));
            wmc.setOutputMarkupId(true);
        }
        add(wmc);
    }

    public ResourceReference getPdfResource() {
        return pdfResource;
    }

    public void setPdfResource(ResourceReference pdfResource) {
        this.pdfResource = pdfResource;
        wmc.add(new AttributeModifier("src", (String) urlFor(pdfResource, null)));
        wmc.setOutputMarkupId(true);
    }
}

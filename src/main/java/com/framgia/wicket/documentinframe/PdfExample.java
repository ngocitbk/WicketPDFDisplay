package com.framgia.wicket.documentinframe;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ByteArrayResource;
import org.apache.wicket.request.resource.IResource;
import org.apache.wicket.request.resource.ResourceReference;

public class PdfExample extends WebPage {

    private static final long serialVersionUID = 1L;
    private PdfViewPanel pdfPanel;

    public PdfExample(final PageParameters parameters) {
        pdfPanel = new PdfViewPanel("pdfPanel");
        pdfPanel.setOutputMarkupId(true);

        add(new AjaxLink<Void>("file1") {

            @Override
            public void onClick(AjaxRequestTarget target) {
                changePdf("file1.pdf", "file1");
                target.add(pdfPanel);
            }

        });

        add(new AjaxLink<Void>("file2") {

            @Override
            public void onClick(AjaxRequestTarget target) {
                changePdf("file2.pdf", "file2");
                target.add(pdfPanel);
            }

        });

        changePdf("file1.pdf", "file1");

        add(pdfPanel);
    }

    public static byte[] bytes(InputStream is) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copy(is, out);
        return out.toByteArray();
    }

    public static void copy(InputStream is, OutputStream os) throws IOException {
        byte[] buf = new byte[1024];
        while (true) {
            int tam = is.read(buf);
            if (tam == -1) {
                return;
            }
            os.write(buf, 0, tam);
        }
    }

    private void changePdf(final String filename, String path) {
        pdfPanel.setPdfResource(new ResourceReference(this.getClass(), path) {

            @Override
            public IResource getResource() {
                try {
                    return new ByteArrayResource("Application/pdf", bytes(PdfExample.class.getResourceAsStream(filename)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}

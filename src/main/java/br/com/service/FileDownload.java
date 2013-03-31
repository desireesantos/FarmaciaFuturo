package br.com.service;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name="FileDownload")
public class FileDownload {

	private StreamedContent file;
	
	public FileDownload() {        
        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/img/logo_sipa.jpg");
		file = new DefaultStreamedContent(stream, "img/jpg", "logo_sipa.jpg");
	}

    public StreamedContent getFile() {
        return file;
    }  
}
    
package com.framgia.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import com.framgia.wicket.documentinframe.PdfExample;

public class MyApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return PdfExample.class; //return default page
	}

}

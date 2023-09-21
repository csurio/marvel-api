package com.siman.assestment.service;

import java.util.Map;

import com.siman.assestment.client.dto.ComicDataWrapper;
import com.siman.assestment.controller.request.ComicsRequestParams;
import com.siman.assestment.controller.request.PaginationRequestParams;

public interface ComicService {
	
	public ComicDataWrapper getAll()
			throws Exception;
	
	public ComicDataWrapper getAll(
				PaginationRequestParams page,
				ComicsRequestParams     params) 
			throws Exception;
	
	public ComicDataWrapper getComicById(
				Map<String, String> pathVars)
			throws Exception;
	
}

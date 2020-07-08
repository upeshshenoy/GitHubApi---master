package com.telstra.codechallenge.controller;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telstra.codechallenge.bean.GitRepoUser;
import com.telstra.codechallenge.service.GitRestApiService;

@RestController
public class GitRestApiController {

	private GitRestApiService gitRestApiService;

	public GitRestApiController(GitRestApiService gitRestApiService) {
		this.gitRestApiService = gitRestApiService;
	}

	@GetMapping(path = "getoldrepousers")
	public List<GitRepoUser> getOldRepoUsers(@RequestParam(required = false) Integer noOfResults)
			throws ClientProtocolException, IOException {
		return gitRestApiService.getOldRepoUsers(noOfResults);
	}

}


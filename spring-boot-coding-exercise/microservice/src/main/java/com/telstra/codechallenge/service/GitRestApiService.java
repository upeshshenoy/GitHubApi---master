package com.telstra.codechallenge.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.telstra.codechallenge.bean.GitRepoUser;
import com.telstra.codechallenge.util.RestApiHandler;

@Service
public class GitRestApiService {

	private static String GITHUB_API_BASE_URL = "https://api.github.com/";
	private static String GITHUB_API_SEARCH_USER_PATH = "search/users?q=";
	private static Gson gson;

	public List<GitRepoUser> getOldRepoUsers(Integer noOfResults) throws ClientProtocolException, IOException {
		List<GitRepoUser> repoUserList = new ArrayList<GitRepoUser>();
		gson = new GsonBuilder().setPrettyPrinting().create();
		String codeContentQuery = "followers:0&sort=joined&order=asc&per_page=" + noOfResults;

		Map<?, ?> repoSearchResult = RestApiHandler.makeRESTApiCall(
				GITHUB_API_BASE_URL + GITHUB_API_SEARCH_USER_PATH + codeContentQuery, "application/vnd.github.preview");
		gson.toJsonTree(repoSearchResult).getAsJsonObject().get("items").getAsJsonArray().forEach(repoUser -> {
			GitRepoUser gitRepoUser = new GitRepoUser();
			gitRepoUser.setLogin(repoUser.getAsJsonObject().get("login").toString());
			gitRepoUser.setHtml_url(repoUser.getAsJsonObject().get("html_url").toString());
			gitRepoUser.setRepoId(repoUser.getAsJsonObject().get("id").toString());
			repoUserList.add(gitRepoUser);

		});
		return repoUserList;

	}

}

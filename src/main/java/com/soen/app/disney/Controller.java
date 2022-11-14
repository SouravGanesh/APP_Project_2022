package com.soen.app.disney;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/register")
public class Controller extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DisneyCharacterSeviceImpl disneyCharacterService = new DisneyCharacterSeviceImpl();

	DisneyCharacterDO characterResponse = null;

	List<DisneyCharacterDO> allCharList = new ArrayList<>();

	List<DisneyCharacterDO> finalAllCharactersList = new ArrayList<>();

	//Runs very first time when the object for this class is created.
	public void init() throws ServletException {
		System.out.println("Starting Server!!!");
		
		try {
			getJsonDataForDisneyCharacters();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getJsonDataForDisneyCharacters() throws IOException {
		URL url = new URL("https://api.disneyapi.dev/characters");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("accept", "application/json");
		InputStream responseStream = connection.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(responseStream));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		disneyCharacterService.saveDisneyCharacters(content);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ObjectMapper MAPPER = new ObjectMapper();
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MAPPER.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String json = "";
		String search = request.getParameter("search");
		String filterBy = request.getParameter("filterBy");
		filterBy = filterBy == null ? "all" : filterBy;
		try {
			allCharList = disneyCharacterService.getDisneyCharacters(search, filterBy);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		try {
			json = MAPPER.writeValueAsString(allCharList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		out.print(json);

		out.close();
	}

}

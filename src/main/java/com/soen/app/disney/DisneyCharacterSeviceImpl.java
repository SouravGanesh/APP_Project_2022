package com.soen.app.disney;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DisneyCharacterSeviceImpl implements DisneyCharacterService {
	@Override
	public void saveDisneyCharacters(StringBuffer content) {
		DisneyCharactersCollectionDO charactersCollectionDO = new DisneyCharactersCollectionDO();
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		int startIndex = content.indexOf("[");
		content.delete(0, startIndex + 1);
		int endIndex = content.lastIndexOf("]");
		content.delete(endIndex, content.length());
		content.append(",");
		System.out.println(content);
		while (true) {
			int end;
			int start;

			if (content.indexOf("{") >= 0) {
				start = content.indexOf("{");
			} else {
				start = 0;
				break;
			}
			if (content.indexOf("}") > 0) {
				end = content.indexOf("}") + 2;
			} else {
				end = 0;
				break;
			}
			if (start >= 0 && end > 0) {
				try {
					System.out.println("Substring = " + content.substring(start, end - 1));
					DisneyCharacterDO characterDO = mapper.readValue(content.substring(start, end - 1),
							DisneyCharacterDO.class);
					charactersCollectionDO.characters.add(characterDO);
					content.delete(start + 1, end + 1);
				} catch (Exception e) {
					continue;
				}
			}

		}
		QueryManager qManager = new QueryManager();
		qManager.createConnection();
		String prepQuery = "DROP TABLE IF EXISTS disney_characters_table";
		String query = "CREATE TABLE disney_characters_table(ID INT(100),NAME VARCHAR(100),IMAGE_URL VARCHAR(200),URL VARCHAR(200),PRIMARY KEY(ID));";
		String prepCreateFilmsTable = "DROP TABLE IF EXISTS CHARACTER_FILMS";
		String createFilmsTable = "CREATE TABLE CHARACTER_FILMS(ID INT NOT NULL AUTO_INCREMENT,CHAR_ID INT,FILM_NAME VARCHAR(50),PRIMARY KEY(ID))";
		String prepTvShowsTable = "DROP TABLE IF EXISTS TV_SHOWS";
		String createTvShowsTable = "CREATE TABLE TV_SHOWS(ID INT NOT NULL AUTO_INCREMENT,CHAR_ID INT,SHOW_NAME VARCHAR(50),PRIMARY KEY(ID))";
		qManager.executeUpdateQuery(prepQuery);
		qManager.executeCreateQuery(query);
		qManager.executeUpdateQuery(prepCreateFilmsTable);
		qManager.executeCreateQuery(createFilmsTable);
		qManager.executeUpdateQuery(prepTvShowsTable);
		qManager.executeCreateQuery(createTvShowsTable);
		for (DisneyCharacterDO disneyCharacterDO : charactersCollectionDO.characters) {
			String insertQuery = "INSERT INTO disney_characters_table(ID,NAME,IMAGE_URL,URL)VALUES(";
			insertQuery += disneyCharacterDO.get_id() + ",";
			insertQuery += "'" + disneyCharacterDO.getName() + "'" + ",";
			insertQuery += "'" + disneyCharacterDO.getImageUrl() + "'" + ",";
			insertQuery += "'" + disneyCharacterDO.getUrl() + "'" + ")";
			insertQuery += ";";
			System.out.println(insertQuery);
			qManager.executeUpdateQuery(insertQuery);
			for (String str : disneyCharacterDO.getFilms()) {
				String insertFilmQuery = "INSERT INTO CHARACTER_FILMS(CHAR_ID,FILM_NAME)VALUES(";
				insertFilmQuery += disneyCharacterDO.get_id() + ",";
				insertFilmQuery += "'" + str + "'" + ");";
				qManager.executeUpdateQuery(insertFilmQuery);
			}
			for (String str : disneyCharacterDO.getTvShows()) {
				String insertFilmQuery = "INSERT INTO TV_SHOWS(CHAR_ID,SHOW_NAME)VALUES(";
				insertFilmQuery += disneyCharacterDO.get_id() + ",";
				insertFilmQuery += "'" + str + "'" + ");";
				qManager.executeUpdateQuery(insertFilmQuery);

			}
		}
	}


	public List<DisneyCharacterDO> getDisneyCharacters(String search, String filterBy) throws SQLException {
		List<DisneyCharacterDO> charactersList = new ArrayList<>();
		String query = "SELECT ID,NAME,IMAGE_URL,URL FROM disney_characters_table";
		if (filterBy.equals("all") && search == "") {
			query = "SELECT disney_characters_table.NAME, disney_characters_table.IMAGE_URL,"
					+ "tv_shows.SHOW_NAME, character_films.FILM_NAME FROM disney_characters_table"
					+ " INNER JOIN tv_shows ON disney_characters_table.ID=tv_shows.ID INNER JOIN character_films"
					+ " ON disney_characters_table.ID=character_films.ID";
		} else if (filterBy.equals("movie")) {
			query = "SELECT disney_characters_table.NAME, disney_characters_table.IMAGE_URL, character_films.FILM_NAME FROM disney_characters_table INNER JOIN character_films ON disney_characters_table.ID=character_films.CHAR_ID;";
		} else if (filterBy.equals("show")) {
			query = "SELECT disney_characters_table.NAME, disney_characters_table.IMAGE_URL, tv_shows.SHOW_NAME FROM disney_characters_table INNER JOIN tv_shows ON disney_characters_table.ID=tv_shows.ID;";
		} else if (search != null && filterBy.equals("show")) {
			query = "SELECT disney_characters_table.NAME, disney_characters_table.IMAGE_URL, tv_shows.SHOW_NAME FROM disney_characters_table INNER JOIN tv_shows ON disney_characters_table.ID=tv_shows.ID AND disney_characters_table.NAME="
					+ search + ";";
		} else if (search != null && filterBy.equals("movie")) {
			query = "SELECT disney_characters_table.NAME, disney_characters_table.IMAGE_URL, character_films.FILM_NAME FROM disney_characters_table INNER JOIN character_films ON disney_characters_table.ID=character_films.ID AND disney_characters_table.NAME="
					+ search + " INNER JOIN character_films ON disney_characters_table.ID=character_films.ID ;";
		} else if (search != null && filterBy.equals("all")) {
			query = "SELECT disney_characters_table.NAME, disney_characters_table.IMAGE_URL, character_films.FILM_NAME,tv_shows.SHOW_NAME FROM disney_characters_table INNER JOIN character_films ON disney_characters_table.ID=character_films.ID AND disney_characters_table.NAME="
					+ search + "  INNER JOIN tv_shows ON disney_characters_table.ID=tv_shows.ID ;";
		}

		QueryManager qManager = new QueryManager();
		qManager.createConnection();
		ResultSet result = qManager.executeQuery(query);
		// System.out.println("Main Query = " + query);
		while (result.next()) {
			// System.out.println("ID=== " + _id);
			String name = result.getString("NAME");
			// System.out.println("Name = " + name);
			String imageUrl = result.getString("IMAGE_URL");
			String films = result.getString("FILM_NAME");
			String showName = result.getString("SHOW_NAME");
			DisneyCharacterDO characterDO = new DisneyCharacterDO(name, imageUrl, showName, films);
			charactersList.add(characterDO);
		}
//        System.out.println("Array list length = " + charactersList.size());
		// System.out.println("List array = " + charactersList.toString());
		return charactersList;
	}


	@Override
	public DisneyCharacterDO getCharacterById(String userId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DisneyCharacterDO getCharacterByName(String characterName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}




}

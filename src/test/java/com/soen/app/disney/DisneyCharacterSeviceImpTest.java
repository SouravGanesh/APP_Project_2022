package com.soen.app.disney;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class DisneyCharacterSeviceImpTest {

	DisneyCharacterSeviceImpl service = new DisneyCharacterSeviceImpl();
	
	@Test
	public void testGetDisneyCharactersWithNoSearchKeywordAndFilterByAll() throws Exception {
		List<DisneyCharacterDO> disneyCharacters = service.getDisneyCharacters("", "all");
		Assert.assertNotNull(disneyCharacters);
	}
	
	@Test
	public void testGetDisneyCharactersWithNoSearchKeywordAndFilterByMovies() throws Exception {
		List<DisneyCharacterDO> disneyCharacters = service.getDisneyCharacters("", "movie");
		Assert.assertNotNull(disneyCharacters);
	}
	
	@Test
	public void testGetDisneyCharactersWithNoSearchKeywordAndFilterByTvShows() throws Exception {
		List<DisneyCharacterDO> disneyCharacters = service.getDisneyCharacters("", "show");
		Assert.assertNotNull(disneyCharacters);
	}
	
	@Test
	public void testGetDisneyCharactersWithSearchKeyword() throws Exception {
		List<DisneyCharacterDO> disneyCharacters = service.getDisneyCharacters("627", "all");
		Assert.assertNotNull(disneyCharacters);
	}
	
	@Test
	public void testGetDisneyCharactersWithSearchKeywordandFilterByMovies() throws Exception {
		List<DisneyCharacterDO> disneyCharacters = service.getDisneyCharacters("627", "movie");
		Assert.assertNotNull(disneyCharacters);
	}
	
	

}

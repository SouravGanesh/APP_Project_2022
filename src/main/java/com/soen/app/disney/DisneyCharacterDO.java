package com.soen.app.disney;

public class DisneyCharacterDO {

	private String[] films;
	private String[] shortFilms;
	private int _id;
	private String name;
	private String imageUrl;
	private String url;

	private String[] videoGames;

	private String[] tvShows;

	private String[] parkAttractions;

	private String[] allies;

	private String[] enemies;
	
	private String finalFilmName;
	
	private String finalShowName;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String[] getFilms() {
		return films;
	}

	public void setFilms(String films[]) {
		this.films = films;
	}

	public String[] getShortFilms() {
		return shortFilms;
	}

	public void setShortFilms(String[] shortFilms) {
		this.shortFilms = shortFilms;
	}

	public String[] getVideoGames() {
		return videoGames;
	}

	public void setVideoGames(String[] videoGames) {
		this.videoGames = videoGames;
	}

	public String[] getTvShows() {
		return tvShows;
	}

	public void setTvShows(String tvShows[]) {
		this.tvShows = tvShows;
	}

	public String[] getParkAttractions() {
		return parkAttractions;
	}

	public void setParkAttractions(String[] parkAttractions) {
		this.parkAttractions = parkAttractions;
	}

	public String[] getAllies() {
		return allies;
	}

	public void setAllies(String[] allies) {
		this.allies = allies;
	}

	public String[] getEnemies() {
		return enemies;
	}

	public void setEnemies(String[] enemies) {
		this.enemies = enemies;
	}
	
	

	public String getFinalFilmName() {
		return finalFilmName;
	}

	public void setFinalFilmName(String finalFilmName) {
		this.finalFilmName = finalFilmName;
	}

	public String getFinalShowName() {
		return finalShowName;
	}

	public void setFinalShowName(String finalShowName) {
		this.finalShowName = finalShowName;
	}

	public DisneyCharacterDO(String[] films, String[] shortFilms, int _id, String name, String imageUrl, String url,
			String[] videoGames, String[] tvShows, String[] parkAttractions, String[] allies, String[] enemies) {

		this._id = _id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.url = url;
		this.videoGames = videoGames;
		this.parkAttractions = parkAttractions;
		this.allies = allies;
		this.enemies = enemies;
	}
	

	public DisneyCharacterDO() {

	}

	public DisneyCharacterDO(int _id, String name, String imageUrl, String url) {
		this._id = _id;
		this.name = name;
		this.imageUrl = imageUrl;
		this.url = url;

	}



	public DisneyCharacterDO(String name, String imageUrl, String tvShow, String films) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.finalShowName=tvShow;
		this.finalFilmName=films;
	}
	
	  @Override
	    public String toString() {
		  return "";
	        /*return "DisneyCharacterDO{" +
	                "films=" + Arrays.toString(films) +
	                ", shortFilms=" + Arrays.toString(shortFilms) +
	                ", _id=" + _id +
	                ", name='" + name + '\'' +
	                ", imageUrl='" + imageUrl + '\'' +
	                ", url='" + url + '\'' +
	                ", videoGames=" + Arrays.toString(videoGames) +
	                ", tvShows=" + Arrays.toString(tvShows) +
	                ", parkAttractions=" + Arrays.toString(parkAttractions) +
	                ", allies=" + Arrays.toString(allies) +
	                ", enemies=" + Arrays.toString(enemies) +
	                '}';*/
	    }


}

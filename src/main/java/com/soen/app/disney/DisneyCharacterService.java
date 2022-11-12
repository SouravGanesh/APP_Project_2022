package com.soen.app.disney;

import java.sql.SQLException;

public interface DisneyCharacterService {
    public void saveDisneyCharacters(StringBuffer content);
    public DisneyCharacterDO getCharacterById(String userId) throws SQLException;
    public DisneyCharacterDO getCharacterByName(String characterName) throws SQLException;
}

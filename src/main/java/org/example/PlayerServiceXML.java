package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class PlayerServiceXML implements PlayerService {


    Path filePathXml = Path.of("players.xml");
    JAXBContext context = JAXBContext.newInstance(Player.class, PlayerWrapper.class);

    /*PlayerWrapper playersXml = new PlayerWrapper(plList);

     Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(playersXml, filePathXml.toFile());*/

    public PlayerServiceXML() throws JAXBException {
    }


    public String getPlayerById(int id) throws JAXBException {


        Unmarshaller unmarshaller = context.createUnmarshaller();
        PlayerWrapper unmarshal = (PlayerWrapper) unmarshaller.unmarshal(filePathXml.toFile());

        for (PlayerWrapper player : unmarshal)
            if (player.getId() == id) {
                return player.getNick();
            }

        return "Нет игрока с таким id";


    }
/*
    // получить список игроков
    public Collection<Player> getPlayers() throws IOException {

        return mapper.readValue(filePath.toFile(), new TypeReference<>() {
        });
    }

    // создать игрока (возвращает id нового игрока)
    public int createPlayer(String nickname) throws IOException {

        List<Player> playersList = mapper.readValue(filePath.toFile(), new TypeReference<>() {
        });
        List<Player> newPlayerList = createNew(nickname, playersList);
        mapper.writeValue(filePath.toFile(), newPlayerList);

        return newPlayerList.getLast().getId();
    }

    // удалить игрока по id'шнику, вернет удаленного игрока
    public Player deletePlayer(int id) throws IOException {
        List<Player> newPlayers = new ArrayList<>();
        List<Player> playersList = mapper.readValue(filePath.toFile(), new TypeReference<>() {
        });
        Player deleted = new Player();
        for (Player player : playersList) {
            if (player.getId() != id) {
                newPlayers.add(player);
            } else {
                deleted = player;
            }
        }
        mapper.writeValue(filePath.toFile(), newPlayers);
        return deleted;

    }

    // добавить очков игроку. Возвращает обновленный счет
    public int addPoints(int playerId, int points) throws IOException {
        List<Player> newPlayers = new ArrayList<>();
        List<Player> playersList = mapper.readValue(filePath.toFile(), new TypeReference<>() {
        });
        int addP = 0;
        for (Player player : playersList) {

            if (player.getId() == playerId) {
                player.setPoints(player.getPoints() + points);
                addP = player.getPoints();
            }
            newPlayers.add(player);

        }
        mapper.writeValue(filePath.toFile(), newPlayers);
        return addP;
    }*/
}








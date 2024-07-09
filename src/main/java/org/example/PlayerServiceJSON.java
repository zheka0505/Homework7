package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;


public class PlayerServiceJSON implements PlayerService {

    Path filePath = Path.of("players.json");
    ObjectMapper mapper = new ObjectMapper();

    // получить игрока по id
    public String getPlayerById(int id) throws IOException {

        List<Player> playersList = mapper.readValue(filePath.toFile(), new TypeReference<>() {
        });
        for (Player player : playersList) {
            if (player.getId() == id) {
                return player.getNick();
            }
        }

        return "Нет игрока с таким id";

    }

    // получить список игроков
    public Collection<Player> getPlayers() throws IOException {

        return mapper.readValue(filePath.toFile(), new TypeReference<>() {
        });
    }

    // создать игрока (возвращает id нового игрока)
    public int createPlayer(String nickname) throws IOException {

        List<Player> playersList = mapper.readValue(filePath.toFile(), new TypeReference<>() {
        });

        int max = 1;
        for (Player player : playersList) {

            if (player.getId() > max) {
                max = player.getId();
            }

        }

        Player newPlayer = new Player();
        newPlayer.setId(max + 1);
        newPlayer.setNick(nickname);
        newPlayer.setPoints(0);
        newPlayer.setOnline(true);

        playersList.add(newPlayer);

        mapper.writeValue(filePath.toFile(), playersList);
        return newPlayer.getId();

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
    }

}





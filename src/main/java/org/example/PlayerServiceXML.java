package org.example;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;


import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class PlayerServiceXML {


    Path filePathXml = Path.of("players.xml");
    JAXBContext context = JAXBContext.newInstance(Player.class, PlayerWrapper.class);
    Unmarshaller unmarshaller = context.createUnmarshaller();
    Marshaller marshaller = context.createMarshaller();

    public PlayerServiceXML() throws JAXBException {
    }


    public String getPlayerById(int id) throws JAXBException {
        PlayerWrapper unmarshal = (PlayerWrapper) unmarshaller.unmarshal(filePathXml.toFile());

        for (Player player : unmarshal.getPlayersXml())
            if (player.getId() == id) {
                return player.getNick();
            }

        return "Нет игрока с таким id";


    }

    // получить список игроков
    public Collection<Player> getPlayers() throws JAXBException {
        PlayerWrapper unmarshal = (PlayerWrapper) unmarshaller.unmarshal(filePathXml.toFile());
        return unmarshal.getPlayersXml();
    }

    // создать игрока (возвращает id нового игрока)
    public int createPlayer(String nickname) throws JAXBException {
        PlayerWrapper unmarshal = (PlayerWrapper) unmarshaller.unmarshal(filePathXml.toFile());
        List<Player> playersList = unmarshal.getPlayersXml();

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

        PlayerWrapper playersXml = new PlayerWrapper(playersList);
        marshaller.marshal(playersXml, filePathXml.toFile());

        return newPlayer.getId();

    }

    // удалить игрока по id'шнику, вернет удаленного игрока
    public Player deletePlayer(int id) throws JAXBException {
        List<Player> newPlayers = new ArrayList<>();
        PlayerWrapper unmarshal = (PlayerWrapper) unmarshaller.unmarshal(filePathXml.toFile());
        List<Player> playersList = unmarshal.getPlayersXml();

        Player deleted = new Player();
        for (Player player : playersList) {
            if (player.getId() != id) {
                newPlayers.add(player);
            } else {
                deleted = player;
            }
        }

        PlayerWrapper playersXml = new PlayerWrapper(newPlayers);
        marshaller.marshal(playersXml, filePathXml.toFile());
        return deleted;

    }

    // добавить очков игроку. Возвращает обновленный счет
    public int addPoints(int playerId, int points) throws JAXBException {
        List<Player> newPlayers = new ArrayList<>();
        PlayerWrapper unmarshal = (PlayerWrapper) unmarshaller.unmarshal(filePathXml.toFile());
        List<Player> playersList = unmarshal.getPlayersXml();

        int addP = 0;
        for (Player player : playersList) {

            if (player.getId() == playerId) {
                player.setPoints(player.getPoints() + points);
                addP = player.getPoints();
            }
            newPlayers.add(player);

        }
        PlayerWrapper playersXml = new PlayerWrapper(newPlayers);
        marshaller.marshal(playersXml, filePathXml.toFile());
        return addP;
    }
}








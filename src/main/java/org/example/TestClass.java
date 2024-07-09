package org.example;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestClass {

    public static void main(String[] args) throws IOException, JAXBException {


        /*Scanner sc = new Scanner(System.in);
        System.out.println("Введите id пользователя:");
        int number = sc.nextInt();

        PlayerServiceJSON j = new PlayerServiceJSON();
        System.out.println(j.getPlayerById(number));

        System.out.println(j.getPlayers());


        System.out.println("Введите ник пользователя:");
        String nick = sc.next();
        System.out.println(j.createPlayer(nick));


        System.out.println("Удалить пользователя с id:");
        int id = sc.nextInt();
        System.out.println(j.deletePlayer(id));

        System.out.println("Укажите id пользователя, которому добавить очков:");
        int idU = sc.nextInt();
        System.out.println("Сколько очков добавить:");
        int points = sc.nextInt();
        System.out.println(j.addPoints(idU, points));*/


        // Obj -> xml

        Path filePathXml = Path.of("players.xml");
        Player pl1 = new Player(4, "ddd", 0,false);
        Player pl2 = new Player(5,"kkk",0,true);


        List<Player> plList = List.of(pl1, pl2);

        JAXBContext context = JAXBContext.newInstance(Player.class, PlayerWrapper.class);
        PlayerWrapper playersXml = new PlayerWrapper(plList);


        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(playersXml, filePathXml.toFile());

        // XML -> Obj
//        Unmarshaller unmarshaller = context.createUnmarshaller();
//        Book unmarshal = (Book) unmarshaller.unmarshal(filePath.toFile());


    }


}

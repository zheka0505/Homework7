package org.example;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.util.Scanner;

public class Json {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        PlayerServiceJSON f = new PlayerServiceJSON();

        // получить игрока по id
        System.out.println("Введите id пользователя:");
        int number = sc.nextInt();
        System.out.println(f.getPlayerById(number));

        // получить список игроков
        System.out.println(f.getPlayers());

        // создать игрока (возвращает id нового игрока)
        System.out.println("Введите ник пользователя:");
        String nick = sc.next();
        System.out.println(f.createPlayer(nick));

        // удалить игрока по id'шнику, вернет удаленного игрока
        System.out.println("Удалить пользователя с id:");
        int id = sc.nextInt();
        System.out.println(f.deletePlayer(id));

        // добавить очков игроку. Возвращает обновленный счет
        System.out.println("Укажите id пользователя, которому добавить очков:");
        int idU = sc.nextInt();
        System.out.println("Сколько очков добавить:");
        int points = sc.nextInt();
        System.out.println(f.addPoints(idU, points));


    }

}

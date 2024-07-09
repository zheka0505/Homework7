package org.example;

import jakarta.xml.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerWrapper {
    @XmlElementWrapper(name = "players")
    @XmlElement(name = "player")

    private List<Player> playersXml;

    public PlayerWrapper() {
    }

    public PlayerWrapper(List<Player> players) {
        this.playersXml = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerWrapper that = (PlayerWrapper) o;
        return Objects.equals(playersXml, that.playersXml);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(playersXml);
    }

    @Override
    public String toString() {
        return "PlayerWrapper{" +
                "playersXml=" + playersXml +
                '}';
    }
}

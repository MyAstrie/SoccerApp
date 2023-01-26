package my.socksapp.service;

import my.socksapp.model.Sock;

import java.util.ArrayList;

public interface SocksService {

    String add(Sock.Color color, Sock.Size size, Integer cottonPart, Integer quantity);
    String delete(Sock.Color color, Sock.Size size, Integer cottonPart, Integer quantity);
    Integer findSocksByCotton(Sock.Color color, Sock.Size size, Integer cottonMin, Integer cottonMax);
    ArrayList<Sock> getAll();
}

package my.socksapp.service.impl;

import lombok.RequiredArgsConstructor;
import my.socksapp.model.Sock;
import my.socksapp.repository.SocksRepository;
import my.socksapp.service.SocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService{

    @Autowired
    private final SocksRepository socksRepository;

    @Override
    public String add(Sock.Color color, Sock.Size size, Integer cottonPart, Integer quantity) {

        Sock newSock = new Sock();
        newSock.setColor(color);
        newSock.setSize(size);
        newSock.setCottonPart(cottonPart);
        newSock.setQuantity(quantity);

        socksRepository.addSock(newSock);

        return newSock.toString();
    }

    @Override
    public String delete(Sock.Color color, Sock.Size size, Integer cottonPart, Integer quantity) {

        Sock sock = new Sock();
        sock.setColor(color);
        sock.setSize(size);
        sock.setCottonPart(cottonPart);

        socksRepository.deleteSock(sock,quantity);

        return "Успешное удаление";
    }

    @Override
    public Integer findSocksByCotton(Sock.Color color, Sock.Size size, Integer cottonMin, Integer cottonMax) {
        return socksRepository.stream()
                .filter(sock -> sock.getColor().equals(color))
                .filter(sock -> sock.getSize().equals(size))
                .filter(sock -> sock.getCottonPart() >= cottonMin)
                .filter(sock -> sock.getCottonPart() <= cottonMax)
                .mapToInt(Sock::getQuantity).sum();
    }

    @Override
    public ArrayList<Sock> getAll(){
        return socksRepository;
    }
}

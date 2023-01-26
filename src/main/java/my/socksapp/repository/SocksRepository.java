package my.socksapp.repository;

import my.socksapp.model.Sock;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class SocksRepository extends ArrayList<Sock> {

    public void addSock(Sock newSock) {
        Optional<Sock> existingSock = this.stream()
                .filter(sock -> sock.toStringWithoutQuantity().equals(newSock.toStringWithoutQuantity()))
                .findFirst();
        if (existingSock.isPresent()) {
            existingSock.get().setQuantity(existingSock.get().getQuantity() + newSock.getQuantity());
        } else {
            this.add(newSock);
        }
    }

    public void deleteSock(Sock sock, int quantity) {
        Sock existingSock = this.stream()
                .filter(s -> s.equals(sock))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Носки не найдены"));
        if (existingSock.getQuantity() < quantity) {
            throw new IllegalArgumentException("Вы пытаетесь удалить больше носков, чем доступно. Доступное количество: " + existingSock.getQuantity());
        }
        existingSock.setQuantity(existingSock.getQuantity() - quantity);
    }
}

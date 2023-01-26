package my.socksapp.controllers;

import lombok.RequiredArgsConstructor;
import my.socksapp.model.Sock;
import my.socksapp.service.SocksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;

@RestController
@RequestMapping("/socks")
@Api(tags = "socks")
@RequiredArgsConstructor
public class SocksController {

    private final SocksService socksService;

    @PostMapping
    @ApiOperation(value = "Добавить новые носки на склад")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "удалось добавить приход"),
            @ApiResponse(code = 400, message = "параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(code = 500, message = "произошла ошибка, не зависящая от вызывающей стороны")})
    public ResponseEntity<String> postSocks(
                        @ApiParam("Color of the sock") @RequestParam("color") Sock.Color color,
                        @ApiParam("Size of the sock") @RequestParam("size") Sock.Size size,
                        @ApiParam("Cotton percentage of the sock") @RequestParam("cottonPart") @Min(0) @Max(100)Integer cottonPart,
                        @ApiParam("Quantity") @RequestParam("quantity") @PositiveOrZero Integer quantity) {
        String message = socksService.add(color, size, cottonPart, quantity).toString();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "Регистрирует отпуск носков со склада")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "удалось произвести отпуск носков со склад"),
            @ApiResponse(code = 400, message = "товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат"),
            @ApiResponse(code = 500, message = "произошла ошибка, не зависящая от вызывающей стороны")})
    public ResponseEntity<String> registersSocksVacation(
            @ApiParam("Color of the sock") @RequestParam("color") Sock.Color color,
            @ApiParam("Size of the sock") @RequestParam("size") Sock.Size size,
            @ApiParam("Cotton percentage of the sock") @RequestParam("cottonPart") @Min(0) @Max(100) Integer cottonPart,
            @ApiParam("Quantity") @RequestParam("quantity") @PositiveOrZero Integer quantity) {
        String message = socksService.delete(color, size, cottonPart, quantity);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "Получить общее количество носков на складе")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "запрос выполнен, результат в теле ответа в виде строкового представления целого числа"),
            @ApiResponse(code = 400, message = "параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(code = 500, message = "произошла ошибка, не зависящая от вызывающей стороны")})
    public ResponseEntity<Integer> findSocksByCotton(
            @ApiParam("Color of the sock") @RequestParam("color") Sock.Color color,
            @ApiParam("Size of the sock") @RequestParam("size") Sock.Size size,
            @ApiParam("Cotton Min part") @RequestParam(required=false, defaultValue="0") @Min(0) @Max(100)  Integer cottonMin,
            @ApiParam("Cotton Max part") @RequestParam(required=false, defaultValue="100") @Min(0) @Max(100) Integer cottonMax) {
        Integer message = socksService.findSocksByCotton(color, size, cottonMin, cottonMax);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Получить все носки на складе")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "произошла ошибка, не зависящая от вызывающей стороны")})
    public ResponseEntity getAllSocks() {
        ArrayList<Sock> temp = socksService.getAll();
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }

    @DeleteMapping
    @ApiOperation(value = "Получить общее количество носков на складе")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "запрос выполнен, товар списан со склада"),
            @ApiResponse(code = 400, message = "параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(code = 500, message = "произошла ошибка, не зависящая от вызывающей стороны")})
    public ResponseEntity<String> writeOffDamagedSocks(
            @ApiParam("Color of the sock") @RequestParam("color") Sock.Color color,
            @ApiParam("Size of the sock") @RequestParam("size") Sock.Size size,
            @ApiParam("Cotton percentage of the sock") @RequestParam("cottonPart") @Min(0) @Max(100) Integer cottonPart,
            @ApiParam("Quantity") @RequestParam("quantity") @PositiveOrZero Integer quantity) {
        String message = socksService.delete(color, size, cottonPart, quantity);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}

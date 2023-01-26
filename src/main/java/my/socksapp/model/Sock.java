package my.socksapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sock {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sock_seq")
    @SequenceGenerator(name = "sock_seq", sequenceName = "sock_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Valid
    @Min(38)
    @Max(43)
    @NotNull(message = "Размер не может быть пустым")
    private Size size;

    @Positive
    @Min(0)
    @Max(100)
    private Integer cottonPart;

    @PositiveOrZero
    private Integer quantity;

    @Valid
    @NotNull(message = "Цвет не может быть пустым")
    private Color color;

    @Override
    public String toString() {
        return "Sock{" +
                "id=" + id +
                ", size=" + size +
                ", cottonPart=" + cottonPart +
                ", quantity=" + quantity +
                ", color=" + color +
                '}';
    }

    public String toStringWithoutQuantity() {
        return "Sock{" +
                "id=" + id +
                ", size=" + size +
                ", cottonPart=" + cottonPart +
                ", color=" + color +
                '}';
    }

    public enum Size {
        EU_38(38),
        EU_39(39),
        EU_40(40),
        EU_41(41),
        EU_42(42),
        EU_43(43);

        private final Integer size;
        private static final Map<Integer,Size> ENUM_MAP;

        Size (Integer size) {
            this.size = size;
        }

        public Integer getSize() {
            return size;
        }

        static {
            Map<Integer,Size> map = new ConcurrentHashMap<>();
            for (Size instance : Size.values()) {
                map.put(instance.getSize(),instance);
            }
            ENUM_MAP = Collections.unmodifiableMap(map);
        }

        public static Size parse(String sizeString) {
            for (Size size : values()) {
                if (size.name().equals(sizeString)) {
                    return size;
                }
            }
            throw new IllegalArgumentException("Invalid size: " + sizeString);
        }

        public static Size parse(int sizeInt) {
            Size size = ENUM_MAP.get(sizeInt);
            if (size != null) {
                return size;
            }
            throw new IllegalArgumentException("Invalid size: " + sizeInt);
        }
    }

    public enum Color{
        BLACK,
        WHITE,
        YELLOW,
        RED,
        GREEN,
        BLUE;

        public static Color parse(String color) {
            for (Color oneColor : values()) {
                if (oneColor.name().equalsIgnoreCase(color)) {
                    return oneColor;
                }
            }
            throw new IllegalArgumentException("Invalid size: " + color);
        }
    }
}

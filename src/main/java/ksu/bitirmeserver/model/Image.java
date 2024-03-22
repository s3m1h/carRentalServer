package ksu.bitirmeserver.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "image")
@Getter
@Setter
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;

    @Column(length = 50000000)
    private byte[] photoByte;

    public Image(){

    }
    public Image(String name, String type, byte[] photoByte) {
        this.name = name;
        this.type = type;
        this.photoByte = photoByte;
    }
}

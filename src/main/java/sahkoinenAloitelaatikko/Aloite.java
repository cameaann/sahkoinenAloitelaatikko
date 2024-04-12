package sahkoinenAloitelaatikko;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

@NamedEntityGraph(name = "Aloite.tekija",
        attributeNodes = {@NamedAttributeNode("tekija")})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Aloite extends AbstractPersistable<Long> {
    private String kuvaus;
    private Date pvm;

    @ManyToOne
    private Tekija tekija;
}

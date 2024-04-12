package sahkoinenAloitelaatikko;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tekija extends AbstractPersistable<Long> {
    private String etunimi;
    private String sukunimi;
    private String email;

    @OneToMany(mappedBy = "tekija")
    private List<Aloite> aloiteet = new ArrayList<>();
}

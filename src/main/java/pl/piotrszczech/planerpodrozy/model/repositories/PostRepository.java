package pl.piotrszczech.planerpodrozy.model.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.piotrszczech.planerpodrozy.model.entities.Post;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long > {


    List<Post> findAllByCountryContains(String country);
    List<Post> findAllByCountryContains(String country, Sort sort);

}

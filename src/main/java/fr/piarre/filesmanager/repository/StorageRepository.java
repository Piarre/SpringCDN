package fr.piarre.filesmanager.repository;

import fr.piarre.filesmanager.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData, Long> {

    Optional<ImageData> findByName(String name);
}

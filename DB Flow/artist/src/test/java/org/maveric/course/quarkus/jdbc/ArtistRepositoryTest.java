package org.maveric.course.quarkus.jdbc;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.sql.SQLException;

import static io.smallrye.common.constraint.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class ArtistRepositoryTest {

    @Inject
    ArtistRepository artistRepository;

    @Test
    public void canCreateAndFindArtist() throws SQLException {

        Artist artist = new Artist("name","bio");
        artistRepository.persist(artist);
        assertNotNull(artist.getId());

        artistRepository.findById(artist.getId());
        assertEquals("name",artist.getName());



    }

}
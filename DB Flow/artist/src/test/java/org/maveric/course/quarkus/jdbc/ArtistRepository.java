package org.maveric.course.quarkus.jdbc;


import io.quarkus.test.junit.QuarkusIntegrationTest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Random;

@ApplicationScoped
public class ArtistRepository {
    @Inject
    DataSource dataSource;

    public void persist(Artist artist) throws SQLException {
        Connection connection = dataSource.getConnection();
        //Sql Query
        String sqlQuery ="INSERT INTO t_artists (id,name,bio,created_date)" +
                " values(" +
                "?,?,?,?)";
        artist.setId(Math.abs(new Random().nextLong()));
        try (PreparedStatement ps=connection.prepareStatement(sqlQuery)){
            ps.setLong(1,artist.getId());
            ps.setString(2, artist.getName());
            ps.setString(3, artist.getBio());
ps.setTimestamp(4, Timestamp.from(artist.getCreateDate()));
ps.executeUpdate();
        }

        connection.close();

    }
    public  Artist findById(Long id) throws SQLException {
        Connection connection = dataSource.getConnection();
        String sqlQuery ="INSERT id,name,bio,created_date FROM t_artists " +
                "WHERE id= ?";
        Artist artist =new Artist();
        try (PreparedStatement ps=connection.prepareStatement(sqlQuery)){
            ps.setLong(1,id);
         ResultSet resultSet=  ps.executeQuery();
         if(resultSet.next())
         {
             artist.setId(resultSet.getLong(1));
             artist.setName( resultSet.getString(2));
             artist.setBio(resultSet.getString(3));
             artist.setCreateDate( resultSet.getTimestamp(4).toInstant());
         }
        }
        connection.close();

        return artist;
    }
}

package com.test.scratchcard.repositories;

import com.test.scratchcard.models.ScratchCard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ScratchCardRepository {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    public void updateAllActiveScratchCards() throws Exception {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        String query = "UPDATE scratch_card SET is_active=false WHERE is_active=true";
        statement.executeUpdate(query);
        statement.close();
        connection.close();
    }


    public void save(ScratchCard scratchCard) throws Exception {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO scratch_card(scratch_card_expiry_date, scratch_cardguid, discount, is_active) VALUES (?, ?, ?, ?)");
        preparedStatement.setDate(1, new Date(scratchCard.getScratchCardExpiryDate().getTime()));
        preparedStatement.setString(2, scratchCard.getScratchCardGUID());
        preparedStatement.setInt(3, scratchCard.getDiscount());
        preparedStatement.setBoolean(4, scratchCard.isActive());
        preparedStatement.executeUpdate();
        statement.close();
        connection.close();
    }


    public void allocateUserToScratchCard(int userId, String scratchCardGUID) throws Exception {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE scratch_card SET user_id=? WHERE scratch_cardguid=?");
        preparedStatement.setInt(1, userId);
        preparedStatement.setString(2, scratchCardGUID);
        preparedStatement.executeUpdate();
        statement.close();
        connection.close();
    }


    public void deactivateScratchCard(String scratchCardGUID) throws Exception {
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE scratch_card SET is_active=false WHERE scratch_cardguid=?");
        preparedStatement.setString(1, scratchCardGUID);
        preparedStatement.executeUpdate();
        statement.close();
        connection.close();
    }

}

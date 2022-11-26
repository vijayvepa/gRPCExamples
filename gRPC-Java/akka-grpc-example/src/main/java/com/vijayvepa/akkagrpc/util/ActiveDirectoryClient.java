package com.vijayvepa.akkagrpc.util;

import com.akkagrpc.akka.ValidateActiveDirectoryDetailsRequest;
import com.vijayvepa.akkagrpc.util.model.ADGroup;
import com.vijayvepa.akkagrpc.util.model.User;

import javax.naming.NamingException;
import java.util.List;

public interface ActiveDirectoryClient {
    String[] userAttributes = {
            "distinguishedName", "cn", "name", "uid",
            "sn", "givenname", "memberOf", "sid",
            "samaccountname", "userPrincipalName",
    };

    List<User> getADUsers(ValidateActiveDirectoryDetailsRequest request) throws NamingException;

    List<ADGroup> getADGroups(ValidateActiveDirectoryDetailsRequest activeDirectoryDetails) throws NamingException;

    Boolean authenticateUser(ValidateActiveDirectoryDetailsRequest activeDirectoryDetails);
}

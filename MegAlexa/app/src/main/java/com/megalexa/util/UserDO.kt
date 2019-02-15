package com.megalexa.util;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.properties.Delegates

@DynamoDBTable(tableName = "megalexa-mobilehub-1331931398-User")

public class UserDO {
    private var _iD : Int by Delegates.notNull<Int>()
    private lateinit var  _birthDate : String
    private lateinit var _firstName : String
    private lateinit var _lastName : String
    private lateinit var _mail : String

    @DynamoDBHashKey(attributeName = "ID")
    @DynamoDBAttribute(attributeName = "ID")
    fun getID() : Int {
        return _iD;
    }

    fun setID( _iD : Int) {
        this._iD = _iD;
    }
    @DynamoDBAttribute(attributeName = "birthDate")
    fun getBirthDate() : String {
        return _birthDate;
    }

    fun setBirthDate( _birthDate : String) {
        this._birthDate = _birthDate;
    }
    @DynamoDBAttribute(attributeName = "firstName")
    fun getFirstName() : String {
        return _firstName;
    }

    fun setFirstName( _firstName : String) {
        this._firstName = _firstName;
    }
    @DynamoDBAttribute(attributeName = "lastName")
    fun getLastName() : String {
        return _lastName;
    }

    fun setLastName( _lastName : String) {
        this._lastName = _lastName;
    }

    @DynamoDBAttribute(attributeName = "mail")
    fun getMail() : String {
        return _mail
    }

    fun setMail( _mail : String) {
        this._mail = _mail
    }

}

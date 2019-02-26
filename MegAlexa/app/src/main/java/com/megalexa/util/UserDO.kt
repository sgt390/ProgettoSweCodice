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
    private lateinit var _iD : String
    private lateinit var _mail : String
    private  var _name : String? = null

    @DynamoDBHashKey(attributeName = "ID")
    @DynamoDBAttribute(attributeName = "ID")
    fun getID() : String {
        return _iD;
    }

    fun setID( _iD : String) {
        this._iD = _iD;
    }


    @DynamoDBAttribute(attributeName = "name")
    fun getName() : String? {
        return _name;
    }

    fun setName( _name : String?) {
        this._name = _name;
    }

    @DynamoDBAttribute(attributeName = "mail")
    fun getMail() : String {
        return _mail
    }

    fun setMail( _mail : String) {
        this._mail = _mail
    }

}
